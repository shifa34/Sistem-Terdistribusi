import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
// import 'package:mahasiswa/mahasiswa/mahasiswa.dart';

class InsertMhs extends StatefulWidget {              //Mendefinisikan kelas InsertMhs sebagai StatefulWidget, yang dapat berubah selama waktu runtime.
  const InsertMhs({super.key});                       //Konstruktor untuk kelas InsertMhs

  @override
  State<InsertMhs> createState() => _InsertMhsState();
}

class _InsertMhsState extends State<InsertMhs> {      //huk
  // final ApiService _apiService = ApiService();
  // final TextEditingController _namaController = TextEditingController();
  // final TextEditingController _emailController = TextEditingController();
  // final TextEditingController _tgllahirController = TextEditingController();

  final nama = TextEditingController();               //Membuat objek TextEditingController untuk mengelola input teks nama mahasiswa.
  final email = TextEditingController();              
  String namaMhs = "";                                // Variabel untuk menyimpan nama mahasiswa.
  String emailMhs = "";       
  DateTime tglLahir = DateTime.now();                 //Variabel menyimpan tanggal lahir mahasiswa, diinisialisasi dengan waktu sekarang.

  Future<void> _pilihTgl(BuildContext context) async {        //Fungsi asinkron untuk menampilkan dialog pemilih tanggal dan mengupdate tanggal lahir jika pengguna memilih tanggal.
    final DateTime? kalender = await showDatePicker(          //
        context: context,
        initialDate: tglLahir,
        firstDate: DateTime(1950),
        lastDate: DateTime(2030));

    if (kalender != null && kalender != tglLahir) {
      setState(() {
        tglLahir = kalender;
      });
    }
  }

  Future<void> InsertMhs() async {                        //Fungsi asinkron untuk menyimpan data mahasiswa ke server menggunakan HTTP POST request.
    String urlInsert = "http://10.0.2.2:9001/api/v1/mahasiswa";
    final Map<String, dynamic> data = {
      "nama": namaMhs,
      "email": emailMhs,
      "tglLahir": '${tglLahir.toLocal()}'.split(' ')[0]
    };

    try {
      var response = await http.post(Uri.parse(urlInsert),
          body: jsonEncode(data),
          headers: {'Content-Type': 'application/json'});

      if (response.statusCode == 200) {
        Navigator.pop(context, "berhasil");

        // Navigator.pushReplacement(
        //   context,
        //   MaterialPageRoute(builder: (context) => Mahasiswa()),
        // );
      } else {
        print(response.statusCode);
      }
    } catch (e) {
      print(e);
    }
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Insert Data Mahasiswa"),
        backgroundColor: Colors.deepOrange.shade400,
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
                "Input Data Mahasiswa",
                style: TextStyle(
                  color: Colors.black,
                  fontSize: 22,
                  fontWeight: FontWeight.bold),
            ),
          ),
              TextField(
                controller: nama,
                decoration: InputDecoration(
                  labelText: "Nama",
                  hintText: "Masukkan Nama",
                  suffixIcon: Align(
                    widthFactor: 1.0,
                    heightFactor: 1.0,
                    child: Icon(
                    Icons.person_outline_rounded,
                    size: 24,
                    color: Colors.black,
                    ),
                  ),
                  fillColor: Colors.orange.shade200,
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                ),
                
              ),
              SizedBox(height: 15),
              TextField(
                controller: email,
                decoration: InputDecoration(
                  labelText: "Email",
                  hintText: "email@gmail.com",
                  suffixIcon: Align(
                    widthFactor: 1.0,
                    heightFactor: 1.0,
                    child: Icon(
                    Icons.email_rounded,
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
                readOnly: true,
                decoration: InputDecoration(
                  labelText: "Tanggal Lahir",
                  border: OutlineInputBorder(
                    borderRadius: BorderRadius.circular(10),
                  ),
                  fillColor: Colors.deepOrange.shade200,
                  prefixIcon: Icon(Icons.calendar_today),
                ),
                onTap: () => _pilihTgl(context),
                controller: TextEditingController(
                  text: "${tglLahir.toLocal()}".split(" ")[0],
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
                    setState(() {
                      namaMhs = nama.text;
                      emailMhs = email.text;
                    });
                    InsertMhs();
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
