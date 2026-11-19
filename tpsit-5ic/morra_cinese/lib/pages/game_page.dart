import 'dart:math';

import 'package:flutter/material.dart';
import 'package:morra_cinese/widget/morra_element.dart';

enum Move {
  sasso('🪨'),
  carta('📄'),
  forbice('✂️');

  const Move(this.emoji);
  final String emoji;
}

enum Result { vittoria, sconfitta, pari }


class GamePage extends StatefulWidget {
  const GamePage({super.key, required this.title});

  final String title;

  @override
  State<GamePage> createState() => _GamePageState();
}

class _GamePageState extends State<GamePage> {
  final Random _random = Random();

  Move? _playerMove;
  Move? _pcMove;
  Result? _result;

  final Map<Move, Move> _winningMoves = {
    Move.sasso: Move.forbice,
    Move.carta: Move.sasso,
    Move.forbice: Move.carta,
  };

  void _play(Move move) {
    setState(() {
      _playerMove = move;
      _pcMove = Move.values[_random.nextInt(Move.values.length)];
      if (_playerMove == _pcMove) {
        _result = Result.pari;
      } else if (_winningMoves[_playerMove] == _pcMove) {
        _result = Result.vittoria;
      } else {
        _result = Result.sconfitta;
      }
    });
  }

  String getResultText() {
    switch (_result) {
      case Result.vittoria:
        return 'Hai vinto!';
      case Result.sconfitta:
        return 'Hai perso!';
      case Result.pari:
        return 'Pareggio!';
      default:
        return '';
    }
  }

  void _resetGame() {
    setState(() {
      _playerMove = null;
      _pcMove = null;
      _result = null;
    });
  }

  @override
Widget build(BuildContext context) {
  final gameOver = _result != null;

  return Scaffold(
    appBar: AppBar(title: Text(widget.title), backgroundColor: Theme.of(context).colorScheme.primaryContainer,
      centerTitle: true),
    body: Center(
      child: Padding(
        padding: const EdgeInsets.all(16),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            // Mossa del PC
            Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                const Text('PC', style: TextStyle(fontSize: 20)),
                Text(
                  _pcMove?.emoji ?? '❓',
                  style: const TextStyle(fontSize: 64),
                ),
              ],
            ),

            const SizedBox(height: 32),

            // Risultato
            Text(
              gameOver ? getResultText() : 'Scegli la tua mossa',
              style: const TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              textAlign: TextAlign.center,
            ),

            const SizedBox(height: 32),

            // Mossa del giocatore
            Column(
              mainAxisSize: MainAxisSize.min,
              children: [
                Text(
                  _playerMove?.emoji ?? '❓',
                  style: const TextStyle(fontSize: 64),
                ),
                const Text('Tu', style: TextStyle(fontSize: 20)),
              ],
            ),

            const SizedBox(height: 32),

            // Scelte oppure "Nuova partita"
            if (gameOver)
              ElevatedButton(
                onPressed: _resetGame,
                style: ElevatedButton.styleFrom(
                  backgroundColor: Theme.of(context).colorScheme.primaryContainer,
                ),
                child: const Text('Nuova partita'),
              )
            else
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: Move.values
                    .map((m) => MorraElement(
                          move: m.emoji,
                          onTap: () => _play(m),
                        ))
                    .toList(),
              ),
          ],
        ),
      ),
    ),
  );
}
}