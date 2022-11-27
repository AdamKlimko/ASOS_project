# Spring: Spring AMQP + Spring Security

Autori:

- Adam Klimko
- Andrej Mikuš
- Marek Mlynček

## Popis
Náš projekt sa venuje dvom témam, repozitár obsahuje aplikáciu s 
ukážkou Spring AMQP a aplikáciu s ukážkou Spring Security.

## Prílohy

[Dokumentácia](dokumentacia.pdf) \
[Prezentácia](prezentacia.pdf)

## Otázka ku skúške 

Ako funguje broker pri protokole AMQP

- [ ] Ukladá správy od publishera do zásobníka aby ich mohol prečítať consumer
- [ ] Ihneď preposiela správy od publishera consumerovi
- [x] Ukladá správy od publishera do fronty aby ich mohol prečítať consumer
- [ ] Ukladá správy od consumera do fronty aby ich mohol prečítať publisher
