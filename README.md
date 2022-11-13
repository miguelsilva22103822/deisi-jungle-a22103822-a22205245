![](diagrama.png?raw=true "Diagrama UML")

Na classe gameManager tem como a funcionalidade controlar o jogo , vimos que faria
sentido ter um objeto que representasse o terreno do 
jogo: a classe Mapa, neste é guardado o numero total de casas , bem como um conjunto de Casas, vimos tambem
que faria sentido ter jogadores no terreno a jogar (e esse o objetivo): a classe Jogador que tem um id e um nome para 
identificar um jogador , uma energia que tem como a função de ver se o jogador consegue andar casas para a frente 
quando for a sua vez de jogar , por fim , um id do Especie. Ainda na classe gameManager, foi preciso uma variavel que 
guardasse o indice do Jogador atual que esta a jogar num certo momento : int indiceJogadorAtual, 
uma variavel que guarda um conjunto de ids do Jogador : int[] iDsJogadores, finalmente uma variavel que 
guarda um conjunto de especies.         
![img.png](img.png)

Na classe casa guarda o numero total de ids presentes, bem como um conjunto de (ids)Jogadores, um objeto que mostra 
o tamanho maximo e um isMeta para verificar se no mapa a casa tem que ter a imagem da meta. 

Na classe Especie , contem um id , um nome e uma imagem para distinguir cada Especie. 



