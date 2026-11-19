
import 'package:calcolatrice/widget/component_widget.dart';
import 'package:flutter/material.dart';

class MainPage extends StatefulWidget {
  const MainPage({super.key});

  @override
  State<MainPage> createState() => _MainPageState();}

  class _MainPageState extends State<MainPage> {
    @override
    Widget build(BuildContext context) {
      return Scaffold(
        appBar: AppBar(
          title: Text("Main Page"),
        ),
        body: Center(
          child: Table(
            border: TableBorder.all(),
            children: [
              //riga 0
              TableRow(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '7'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '8'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '9'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: 'x'),
                  ),
                ],
              ),
              //riga 1
              TableRow(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '4'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '5'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '6'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '-'),
                  ),
                ],
              ),
              //riga 2
              TableRow(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '1'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '2'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '3'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '+'),
                  ),
                ],
              ),
              //riga 3
              TableRow(
                children: [
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '+/-'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '0'),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: ','),
                  ),
                  Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ComponentWidget( description: '='),
                  ),
                ],
              ),
                ],

              
            
          ),
        ),
      );
    }
  }
  
