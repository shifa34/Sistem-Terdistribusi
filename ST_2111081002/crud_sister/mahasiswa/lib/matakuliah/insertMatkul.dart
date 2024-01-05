import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class InsertMatkul extends StatefulWidget {
  const InsertMatkul({super.key});

  @override
  State<InsertMatkul> createState() => _InsertMatkulState();
}

class _InsertMatkulState extends State<InsertMatkul> {
  final kode = TextEditingController();
  final nama = TextEditingController();
  final sks = TextEditingController();

  bool isNumeric(String str) {
    // ignore: unnecessary_null_comparison
    if (str == null || str.isEmpty) {
      return false;
    }
    final format = RegExp(r'^[0-9]+$');
    return format.hasMatch(str);
  }

  Future<void> insertMatakuliah() async {
    if (isNumeric(sks.text)) {
      String urlInsert = "http://192.168.56.1:9002/api/v1/matakuliah";
      final Map<String, dynamic> data = {
        "kode": kode.text,
        "nama": nama.text,
        "sks": int.parse(sks.text)
      };

      try {
        var response = await http.post(Uri.parse(urlInsert),
            body: jsonEncode(data),
            headers: {'Content-Type': 'application/json'});

        if (response.statusCode == 200) {
          Navigator.pop(context);
        } else {
          print("Gagal");
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
        title: Text("Insert Data Matakuliah"),
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
              Padding(
                padding: EdgeInsets.fromLTRB(30, 40, 30, 40),
                child: Text(
                "Input Data Matakuliah",
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 22,
                  fontWeight: FontWeight.bold),
            ),
          ),
              TextField(
                decoration: InputDecoration(
                  labelText: "Kode",
                  hintText: "Masukkan Kode Matakuliah",
                  suffixIcon: Align(
                    widthFactor: 1.0,
                    heightFactor: 1.0,
                    child: Icon(
                    Icons.code,
                    size: 24,
                    color: Colors.black,
                    ),
                  ),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                controller: kode,
              ),
              SizedBox(height: 15),
              TextField(
                controller: nama,
                decoration: InputDecoration(
                  labelText: "Nama Matakuliah",
                  hintText: "Masukkan Nama Matakuliah",
                  suffixIcon: Align(
                    widthFactor: 1.0,
                    heightFactor: 1.0,
                    child: Icon(
                    Icons.code,
                    size: 24,
                    color: Colors.black,
                    ),
                  ),
                  fillColor: Colors.deepOrange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
              ),
              SizedBox(height: 15),
              TextField(
                controller: sks,
                decoration: InputDecoration(
                  labelText: "SKS",
                  hintText: "Masukkan Jumlah SKS",
                  suffixIcon: Align(
                    widthFactor: 1.0,
                    heightFactor: 1.0,
                    child: Icon(
                    Icons.code,
                    size: 24,
                    color: Colors.black,
                    ),
                  ),
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