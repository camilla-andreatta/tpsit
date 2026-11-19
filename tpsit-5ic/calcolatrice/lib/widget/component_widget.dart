import 'package:flutter/material.dart';

class ComponentWidget extends StatelessWidget {
  final String description;

  const ComponentWidget({
    Key? key,

    required this.description,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              description,
              style: Theme.of(context).textTheme.titleLarge,
            ),  
            
          ],
        ),
      ),
    );
  }
}