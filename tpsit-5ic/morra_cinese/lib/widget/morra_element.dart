import 'package:flutter/material.dart';

class MorraElement extends StatelessWidget {
  const MorraElement({super.key, required this.move, required this.onTap});

  final String move;
  final VoidCallback onTap;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap,
      child: Container(
        padding: const EdgeInsets.all(16),
        decoration: BoxDecoration(
          color: Theme.of(context).colorScheme.primaryContainer,
          borderRadius: BorderRadius.circular(16), 
          border: Border.all(
            color: Theme.of(context).colorScheme.primary,
            width: 2,
          ),
        ),
        child: Text(move, style: const TextStyle(fontSize: 48)),
      ),
    );
  }
}