import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mahasiswa/mahasiswa/insertMhs.dart';
import 'package:mahasiswa/mahasiswa/updateMhs.dart';
import 'package:mahasiswa/nilai/nilaiMahasiswa.dart';

class Mahasiswa extends StatefulWidget {
  const Mahasiswa({super.key});

  @override
  State<Mahasiswa> createState() => _MahasiswaState();
}

class _MahasiswaState extends State<Mahasiswa> {
  List listMahasiswa = [];

  @override
  void initState() {
    allMahasiswa();
    super.initState();
  }

  Future<void> allMahasiswa() async {
    String urlMahasiswa = "http://10.0.2.2:9001/api/v1/mahasiswa";
    try {
      var response = await http.get(Uri.parse(urlMahasiswa));
      print("Status Code: ${response.statusCode}");
      print("Response Body: ${response.body}");

      listMahasiswa = jsonDecode(response.body);
      setState(() {
        listMahasiswa = jsonDecode(response.body);
      }
      
      );
    } catch (exc) {
      print(exc);
    }
  }

  Future<void> deleteMahasiwa(int id) async {
    String urlMahasiswa = "http://10.0.2.2:9001/api/v1/mahasiswa/${id}";
    try {
      await http.delete(Uri.parse(urlMahasiswa));
      setState(() {
        allMahasiswa();
      });
    } catch (exc) {
      print(exc);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Data Mahasiswa"),
        backgroundColor: Color.fromARGB(255, 245, 100, 68),
      ),
      body: listMahasiswa.isEmpty
          ? Center(
              child: Text(
                'Tidak ada data',
                style: TextStyle(fontSize: 20),
              ),
            )
          : ListView.builder(
              itemCount: listMahasiswa.length,
              itemBuilder: (context, index) {
                return Card(
                  margin: EdgeInsets.all(5),
                  child: ListTile(
                    leading: Icon(
                      Icons.people,
                      color: Colors.deepOrange.shade500,
                      size: 24,
                    ),
                    title: Text(
                      listMahasiswa[index]["nama"]?.toString() ?? "",
                      style: TextStyle(
                          color: Colors.orange,
                          fontSize: 17,
                          fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text(
                      "Email : ${listMahasiswa[index]["email"]?.toString() ?? ""}\nTanggal Lahir : ${listMahasiswa[index]["tglLahir"]?.toString() ?? ""}",
                      style: TextStyle(
                          color: Colors.black,
                          fontSize: 13,
                          fontWeight: FontWeight.normal),
                    ),
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        IconButton(
                          tooltip: "Lihat Nilai",
                          onPressed: () {
                            Navigator.push(
                                context,
                                MaterialPageRoute(
                                    builder: (context) => NilaiMahasiswa(
                                        listMahasiswa[index]["id"])));
                          },
                          icon: Icon(
                            Icons.grade,
                            color: Colors.amber,
                            size: 24,
                          ),
                        ),
                        IconButton(
                          tooltip: "Hapus Data",
                          onPressed: () {
                            deleteMahasiwa(listMahasiswa[index]["id"]);
                          },
                          icon: Icon(
                            Icons.delete,
                            color: Colors.deepOrange.shade800,
                            size: 24,
                          ),
                        ),
                        IconButton(
                          tooltip: "Edit Data",
                          onPressed: () {
                            Navigator.push(context,
                                MaterialPageRoute(builder: (context) {
                              return UpdateMhs(
                                  listMahasiswa[index]["id"] ?? "",
                                  listMahasiswa[index]["nama"] ?? "",
                                  listMahasiswa[index]["email"] ?? "",
                                  DateTime.parse(
                                      listMahasiswa[index]["tglLahir"]));
                            })).then((value) => allMahasiswa());
                          },
                          icon: Icon(
                            Icons.edit,
                            color: Colors.deepOrange.shade300,
                            size: 24,
                          ),
                        ),
                      ],
                    ),
                  ),
                );
              }),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: Container(
        margin: EdgeInsets.only(bottom: 16.0, right: 16.0),
        child: FloatingActionButton(
          onPressed: () {
            Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => InsertMhs()))
                .then((value) => allMahasiswa());
          },
          child: Icon(Icons.add),
          backgroundColor: Color.fromARGB(255, 245, 100, 68),
        ),
      ),
    );
  }
}
