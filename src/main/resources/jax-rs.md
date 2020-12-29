# REST JAX_RS

## Descrição

A necessidade de trocar informações entre aplicações motivou diferentes abordagens para "integração de dados". Este
curso foca-se em aprender a solução de integração denominada webservices.

O JAX-RS é uma especificação que permite criar RESTful webservices.

O JAX-RS foca bastante URIs e nos detalhes do protocolo HTTP para se beneficiar de seus recursos.

Para utilizar os recursos do nosso servidor são utilizados diversas anotações correspondentes aos verbos HTTP, onde os
mais utilizados são:

- GET: Buscar dados;
- POST: Utilizado para criar informações;
- PUT: utilizado para alterar informações;
- DELETE: Remover dados;

## Extraindo valores dos métodos HTTP:

### @PathVariable

Especifica que o valor do parâmetro será indicado na URI.

EX:

```html
    meusite.com.br/usuario/{id} 
```

### @RequestParam

Extrai o valor do parâmetro da URI.

EX:

```html
    ?idade=10?uf=PA 
```

### @RequestBody

Recebe os valores no payload da requisição.

## Response

### ResponseEntity

Representa toda resposta HTTP. Você pode controlar qualquer coisa que aconteça, código de status, cabeçalho e corpo.
