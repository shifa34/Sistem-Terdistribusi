import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class InsertNilai extends StatefulWidget {
  const InsertNilai({super.key});

  @override
  State<InsertNilai> createState() => _InsertNilaiState();
}

class _InsertNilaiState extends State<InsertNilai> {
  List<Map<String, dynamic>> namaMahasiswa = [];
  List<Map<String, dynamic>> namaMatakuliah = [];
  int? idMahasiswa;
  int? idMatakuliah;
  final nilai = TextEditingController();

  bool isNumeric(String str) {
    // ignore: unnecessary_null_comparison
    if (str == null || str.isEmpty) {
      return false;
    }
    final format = RegExp(r'^[0-9]+(\.[0-9]+)?$');
    return format.hasMatch(str);
  }

  Future<void> insertMatakuliah() async {
    if (isNumeric(nilai.text)) {
      String urlInsert = "http://10.0.2.2:9003/api/v1/nilai";
      final Map<String, dynamic> data = {
        "mahasiswa_id": idMahasiswa,
        "matakuliah_id": idMatakuliah,
        "nilai": int.parse(nilai.text)
      };

      try {
        var response = await http.post(Uri.parse(urlInsert),
            body: jsonEncode(data),
            headers: {'Content-Type': 'application/json'});

        if (response.statusCode == 200) {
          Navigator.pop(context, "berhasil");
        } else {
          print("Gagal");
        }
      } catch (e) {
        print(e);
      }
    } else {
      print("Bukan Angka Desimal");
    }
  }

  @override
  void initState() {
    super.initState();
    getMahasiswa();
    getMatakuliah();
  }

  Future<void> getMahasiswa() async {
    String urlMahasiswa = "http://10.0.2.2:9001/api/v1/mahasiswa";
    try {
      var response = await http.get(Uri.parse(urlMahasiswa));
      final List<dynamic> dataMhs = jsonDecode(response.body);
      setState(() {
        namaMahasiswa = List.from(dataMhs);
      });
    } catch (exc) {
      print(exc);
    }
  }

  Future<void> getMatakuliah() async {
    String urlMatakuliah = "http://10.0.2.2:9002/api/v1/matakuliah";
    try {
      var response = await http.get(Uri.parse(urlMatakuliah));
      final List<dynamic> dataMk = jsonDecode(response.body);
      setState(() {
        namaMatakuliah = List.from(dataMk);
      });
    } catch (exc) {
      print(exc);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Insert Data Nilai"),
        backgroundColor: Colors.deepOrange,
      ),
      body: Center(
        child: Container(
          margin: EdgeInsets.fromLTRB(0, 0, 0, 0),
          width: 800,
          padding: EdgeInsets.all(16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              Padding(
                padding: EdgeInsets.fromLTRB(30, 10, 30, 40),
                child: Text(
                "Input Nilai Mahasiswa",
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 22,
                  fontWeight: FontWeight.bold),
            ),
          ),
              DropdownButtonFormField(
                value: null,
                onChanged: (value) {
                  setState(() {
                    idMahasiswa = int.parse(value.toString());
                  });
                },
                items: namaMahasiswa.map((item) {
                  return DropdownMenuItem(
                      value: item["id"].toString(), child: Text(item["nama"]));
                }).toList(),
                decoration: InputDecoration(
                  labelText: "ID Mahasiswa",
                  hintText: "Pilih Mahasiswa",
                  // prefixIcon: Icon(Icons.person_pin_outlined),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 15),
              DropdownButtonFormField(
                value: null,
                onChanged: (value) {
                  setState(() {
                    idMatakuliah = int.parse(value.toString());
                  });
                },
                items: namaMatakuliah.map((item) {
                  return DropdownMenuItem(
                      value: item["id"].toString(),
                      child: Text(item["nama"].toString()));
                }).toList(),
                decoration: InputDecoration(
                  labelText: "ID Matakuliah",
                  hintText: "Pilih Matakuliah",
                  // prefixIcon: Icon(Icons.bookmark_border),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 15),
              TextField(
                controller: nilai,
                decoration: InputDecoration(
                  labelText: "Nilai",
                  hintText: "Masukkan Nilai Mahasiswa",
                  suffixIcon: Align(
                    widthFactor: 1.0,
                    heightFactor: 1.0,
                    child: Icon(
                    Icons.edit,
                    size: 24,
                    color: Colors.black,
                    ),
                  ),
                  //prefixIcon: Icon(Icons.edit),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 15),
              SizedBox(
                height: 50,
                width: 200,
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: Colors.deepOrange.shade400,
                  ),
                  onPressed: () {
                    insertMatakuliah();
                  },
                  child: Text(
                    "SIMPAN",
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 20,
                    ),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
