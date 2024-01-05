import 'package:flutter/material.dart';
import 'package:mahasiswa/mahasiswa/mahasiswa.dart';
import 'package:mahasiswa/matakuliah/matakuliah.dart';
import 'package:mahasiswa/nilai/nilai.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Java Flutter Service GUI',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepOrange),
        useMaterial3: true,
      ),
      debugShowCheckedModeBanner: false,
      home: Navbar(),
    );
  }
}

class Navbar extends StatefulWidget {
  const Navbar({super.key});

  @override
  _NavbarState createState() => _NavbarState();
}

class _NavbarState extends State<Navbar> {
  int _currentIndex = 0;
  final List<Widget> _pages = [
    Mahasiswa(),
    Matakuliah(),
    Nilai(),
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: _pages[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        onTap: (index) {
          setState(() {
            _currentIndex = index;
          });
        },
        items: [
          BottomNavigationBarItem(
              icon: Icon(Icons.people),
              label: 'Mahasiswa',
              backgroundColor: Colors.red),
          BottomNavigationBarItem(
              icon: Icon(Icons.bookmark),
              label: 'Matakuliah',
              backgroundColor: Colors.green),
          BottomNavigationBarItem(
              icon: Icon(Icons.edit),
              label: 'Nilai',
              backgroundColor: Colors.blue),
        ],
      ),
    );
  }
}