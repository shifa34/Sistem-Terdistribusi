import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

// ignore: must_be_immutable
class UpdateMatkul extends StatefulWidget {
  // const UpdateMatkul({super.key});
  int idUpdate;
  String kodeUpdate;
  String namaUpdate;
  String sksUpdate;
  UpdateMatkul(this.idUpdate, this.kodeUpdate, this.namaUpdate, this.sksUpdate);

  @override
  State<UpdateMatkul> createState() => _UpdateMatkulState();
}

class _UpdateMatkulState extends State<UpdateMatkul> {
  int id = 0;
  final kode = TextEditingController();
  final nama = TextEditingController();
  final sks = TextEditingController();

  @override
  void initState() {
    kode.text = widget.kodeUpdate;
    nama.text = widget.namaUpdate;
    sks.text = widget.sksUpdate;
    id = widget.idUpdate;

    super.initState();
  }

  bool isNumeric(String str) {
    // ignore: unnecessary_null_comparison
    if (str == null || str.isEmpty) {
      return false;
    }
    final format = RegExp(r'^[0-9]+$');
    return format.hasMatch(str);
  }

  Future<void> UpdateMatkul() async {
    if (isNumeric(sks.text)) {
      String urlUpdate =
          "http://10.0.2.2:9002/api/v1/matakuliah/${id}?kode=${kode.text}&&nama=${nama.text}&&sks=${sks.text}";
      try {
        var response = await http.put(Uri.parse(urlUpdate));

        if (response.statusCode == 200) {
          Navigator.pop(context);
        } else {
          print(response.statusCode);
        }
      } catch (e) {
        print(e);
      }
    } else {
      print("Bukan Angka");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Update Data Matakuliah"),
        backgroundColor: Color.fromARGB(255, 245, 100, 68),
      ),
      body: Center(
        child: Container(
          margin: EdgeInsets.fromLTRB(0, 100, 0, 0),
          width: 800,
          padding: EdgeInsets.all(16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            children: [
              TextField(
                decoration: InputDecoration(
                  labelText: "Kode",
                  hintText: "Ketikkan Kode Matakuliah",
                  prefixIcon: Icon(Icons.code),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                controller: kode,
              ),
              SizedBox(height: 10),
              TextField(
                controller: nama,
                decoration: InputDecoration(
                  labelText: "Nama",
                  hintText: "Ketikkan Nama Matakuliah",
                  prefixIcon: Icon(Icons.book),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 10),
              TextField(
                controller: sks,
                decoration: InputDecoration(
                  labelText: "SKS",
                  hintText: "Ketikkan Jumlah SKS",
                  prefixIcon: Icon(Icons.numbers_rounded),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 20),
              SizedBox(
                height: 50,
                width: 200,
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: Colors.deepOrange.shade400,
                  ),
                  onPressed: () {
                    UpdateMatkul();
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
