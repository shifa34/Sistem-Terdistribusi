import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mahasiswa/nilai/insertNilai.dart';
import 'package:mahasiswa/nilai/updateNilai.dart';

class Nilai extends StatefulWidget {
  const Nilai({super.key});

  @override
  State<Nilai> createState() => _NilaiState();
}

class _NilaiState extends State<Nilai> {
   List<Map<String, dynamic>> namaMahasiswa = [];
  List<Map<String, dynamic>> namaMatakuliah = [];
  List listNilai = [];

  @override
  void initState() {
    allNilai();
    getMahasiswa();
    getMatakuliah();
    super.initState();
  }

  Future<void> getMahasiswa() async {
    String urlMahasiswa = "http://192.168.56.1:9001/api/v1/mahasiswa";
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
    String urlMatakuliah = "http://192.168.56.1:9002/api/v1/matakuliah";
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

  Future<void> allNilai() async {
    String urlNilai = "http://192.168.56.1:9003/api/v1/nilai";
    try {
      var response = await http.get(Uri.parse(urlNilai));
      listNilai = jsonDecode(response.body);
      setState(() {
        listNilai = jsonDecode(response.body);
      });
    } catch (exc) {
      print(exc);
    }
  }

  Future<void> deleteNilai(int id) async {
    String urlNilai = "http://192.168.56.1:9003/api/v1/nilai/${id}";
    try {
      await http.delete(Uri.parse(urlNilai));
      setState(() {
        allNilai();
      });
    } catch (exc) {
      print(exc);
    }
  }
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Data Nilai"),
        backgroundColor: const Color.fromARGB(255, 245, 100, 68),
      ),
      body: listNilai.isEmpty
          ? Center(
              child: Text(
                'Tidak ada data',
                style: TextStyle(fontSize: 20),
              ),
            )
          : ListView.builder(
              itemCount: listNilai.length,
              itemBuilder: (context, index) {
                return Card(
                  margin: EdgeInsets.all(5),
                  child: ListTile(
                    leading: Icon(
                      Icons.pin_outlined,
                      color: Colors.green.shade500,
                      size: 24,
                    ),
                    title: Text(
                      "${namaMahasiswa.firstWhere((mahasiswa) => mahasiswa["id"] == listNilai[index]["idMahasiswa"], orElse: () => {})["nama"] ?? ""}",
                      style: TextStyle(
                          color: Colors.green,
                          fontSize: 17,
                          fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text(
                      "Matakuliah: ${namaMatakuliah.firstWhere((matakuliah) => matakuliah["id"] == listNilai[index]["idMatakuliah"], orElse: () => {})["nama"] ?? ""}\nNilai: ${listNilai[index]["nilai"]}",
                      style: TextStyle(
                          color: Colors.black,
                          fontSize: 13,
                          fontWeight: FontWeight.normal),
                    ),
                    trailing: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        IconButton(
                            tooltip: "Hapus Data",
                            onPressed: () {
                              deleteNilai(listNilai[index]["id"]);
                            },
                            icon: Icon(
                              Icons.delete,
                              color: Colors.red.shade300,
                              size: 24,
                            )),
                        IconButton(
                            tooltip: "Edit Data",
                            onPressed: () {
                              Navigator.push(
                                      context,
                                      MaterialPageRoute(
                                          builder: (context) => UpdateNilai(
                                              listNilai[index]["id"],
                                              listNilai[index]["idMahasiswa"],
                                              listNilai[index]["idMatakuliah"],
                                              listNilai[index]["nilai"])))
                                  .then((value) => allNilai());
                            },
                            icon: Icon(
                              Icons.edit,
                              color: Colors.yellow.shade800,
                              size: 24,
                            )),
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
                    MaterialPageRoute(builder: (context) => InsertNilai()))
                .then((value) => allNilai());
          },
          child: Icon(Icons.add),
          backgroundColor: Color.fromARGB(255, 245, 100, 68),
        ),
      ),
    );
  }
}