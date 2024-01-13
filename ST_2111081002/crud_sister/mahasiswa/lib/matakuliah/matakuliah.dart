import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:mahasiswa/matakuliah/insertMatkul.dart';
import 'package:mahasiswa/matakuliah/updateMatkul.dart';

class Matakuliah extends StatefulWidget {
  const Matakuliah({super.key});

  @override
  State<Matakuliah> createState() => _MatakuliahState();
}

class _MatakuliahState extends State<Matakuliah> {
  List listMatakuliah = [];

  @override
  void initState() {
    allMatakuliah();
    super.initState();
  }

  Future<void> allMatakuliah() async {
    String urlMatakuliah = "http://10.0.2.2:9002/api/v1/matakuliah";
    try {
      var response = await http.get(Uri.parse(urlMatakuliah));
      listMatakuliah = jsonDecode(response.body);
      setState(() {
        listMatakuliah = jsonDecode(response.body);
      });
    } catch (exc) {
      print(exc);
    }
  }

  Future<void> deleteMatakuliah(int id) async {
    String urlMatakuliah = "http://10.0.2.2:9002/api/v1/matakuliah/${id}";
    try {
      await http.delete(Uri.parse(urlMatakuliah));
      setState(() {
        allMatakuliah();
      });
    } catch (exc) {
      print(exc);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Data Matakuliah"),
        backgroundColor: const Color.fromARGB(255, 245, 100, 68),
      ),
      body: listMatakuliah.isEmpty
          ? Center(
              child: Text(
                'Tidak ada data',
                style: TextStyle(fontSize: 20),
              ),
            )
          : ListView.builder(
              itemCount: listMatakuliah.length,
              itemBuilder: (context, index) {
                return Card(
                  margin: EdgeInsets.all(5),
                  child: ListTile(
                    leading: Icon(
                      Icons.book,
                      color: Colors.deepOrange.shade500,
                      size: 24,
                    ),
                    title: Text(
                      listMatakuliah[index]["kode"]?.toString() ?? "",
                      style: TextStyle(
                          color: Colors.orange,
                          fontSize: 17,
                          fontWeight: FontWeight.bold),
                    ),
                    subtitle: Text(
                      "Matakuliah : ${listMatakuliah[index]["nama"]?.toString() ?? ""}\nSKS : ${listMatakuliah[index]["sks"]?.toString() ?? ""}",
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
                              deleteMatakuliah(listMatakuliah[index]["id"]);
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
                                          builder: (context) =>
                                              UpdateMatkul(
                                                  listMatakuliah[index]["id"],
                                                  listMatakuliah[index]["kode"],
                                                  listMatakuliah[index]["nama"],
                                                  listMatakuliah[index]["sks"]
                                                      .toString())))
                                  .then((value) => allMatakuliah());
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
                    MaterialPageRoute(builder: (context) => InsertMatkul()))
                .then((value) => allMatakuliah());
          },
          child: Icon(Icons.add),
          backgroundColor: Color.fromARGB(255, 245, 100, 68),
        ),
      ),
    );
  }
}
