<h1 align="center" id="Sobre">
    🏠 MoreAqui
</h1>
<p align="center">
Aplicação para venda e compra de Imoveis
<br>

<img src="https://img.shields.io/static/v1?label=APP&message=moreaqui&color=blue&style=for-the-badge&logo=bilibili"/>
<h4 align="center"> 
	🚧  Projeto Finalizado Academicamente ⌨️ Projeto continuará em desenvolvimento...  🚧
</h4></p>
<h1 align="center">
	<img height="300px" src="https://trello-attachments.s3.amazonaws.com/5fab24bc7bf4741b1ef08525/5fab2c022505f36785b7a92c/9af8563ff9ce71124e33b6763671461c/Screenshot_20201205-154440_MoreAqui.jpg"/>
	<img height="300px" src="https://trello-attachments.s3.amazonaws.com/5fab24bc7bf4741b1ef08525/5fab2c471726b967b4419216/9c5c091d43d9dbd963455c4a4efce580/Screenshot_20201204-230213_MoreAqui.jpg"/>
	<img height="300px" src="https://trello-attachments.s3.amazonaws.com/5fab24bc7bf4741b1ef08525/5fab4a7875ff9c5a3d5646c3/c723a7d66f8ca14c57eab11bb173a62a/Screenshot_20201204-230222_MoreAqui.jpg"/>
	<img height="300px" src="https://trello-attachments.s3.amazonaws.com/5fab4a8d86b8738b680edf89/428x861/4e0ec75f4e0f707fb1e4ca3c01fd7080/image.png"/>
	
</h1>

### ⌨️Desenvolvimento

<!--ts-->
   * [Sobre](#sobre)
   * [⌨️Desenvolvimento](#desenvolvimento)
   * [✨Features](#features)
   * [📦Fases](#fases)
     * [Fase 1](#fase-1)
     * [Fase 2](#fase-2)
     * [Fase 3](#fase-3)
     * [Fase 4](#fase-4)
     * [Fase 5](#fase-5)
   
<!--te-->
### Sobre

Projeto iniciado com objetivo acadêmico para cadeira de Desenvolvimento para dispositivos móveis, UniFG Recife-PE <br>
Grupo 11 <br>
GitHub:https://github.com/LucaoMendes/MoreAqui <br>
Trello:https://trello.com/b/XstseyJW/moreaqui <br>
Desenvolvedores: <br>
Lucas Vinicius Silva Mendes - Mat. 201806442 <br>
João Gabriel da Silva - Mat. 201805070 <br>
Lucas Eduardo M de Amorim - Mat. 201708075 <br>
Marcos Vinicius Silva - Mat. 201900939 <br>
Igor Bezerra Borges de Lima - Mat. 202005035

### ✨Features

- [x] Cadastro de Imoveis
- [x] Remoção de Imoveis
- [ ] Edição de Imoveis
- [x] Localização em tempo real
- [x] Busca por imoveis
- [x] Visualização de todos os imoveis cadastrados


### 📦Fases

O Projeto inicialmente foi dividido em 5 fases pelo professor da cadeira de desenvolvimento mobile, sendo a primeira fase responsável pelas duas primeiras activities do projeto, somente visual nada implementado até então. Segunda fase responsável pelo tratamento de dados inseridos no sistema e criação do banco local com sqlLite. A terceira parte teve como objetivo salvar os dados do banco local em um servidor remoto e opicionalmente poderiamos implementar (foi implementado) a função de apagar os dados do servidor local depois de serem salvos remotamente. E por ultimo, a fase 5 que teve como objetivo a funcionalidade do mapa e suas marcações com os imoveis inseridos.

### Fase 1
Objetivos:

- [x] Criar uma atividade para a aplicação com os 3 botões principais
- [x] Criar o onClickListener para o botão Novo
- [x] Criar uma Activity insert onde serão inseridos as informações dos Imoveis
- [x] Por hora o valor do telefone era tratado como int
- [x] Mensagem de alerta quando o valor do telefone era invalido
- [x] Sempre que um número válido for inserido, imprima o objeto criado no log do Android
- [x] Para avisar o usuário que o novo dado foi inserido com sucesso, utilize uma caixa de alerta.

### Fase 2
Objetivos:

- [x] Crie uma class EstatesData que estenda a classe SQLiteOpenHelper .
- [x] O seu banco de dados deverá ter as seguintes colunas: • ID, TYPE (o tipo do imóvel, ex. casa, loja, etc), SIZE (o tamanho do imóvel, ex., pequeno, médio, etc), STATUS (se o imóvel está pronto, em construção, possui moradores, etc) e PHONE, o contato do vendedor.
- [x] Modifique sua atividade InsertActivity para utilizar um banco de dados
- [x] Sempre que um imóvel válido for cadastrado, esse imóvel deverá ser inserido nesse banco de dados, em vez de ser impresso no log do Android Studio.
- [x] Mude o nome do botão Procurar para Visualizar
- [x] Crie um gerenciador de eventos para lidar com o botão Visualizar na atividade principal.
- [x] Esse botão deverá abrir uma nova atividade que exiba as informações cadastradas no banco de dados.
- [x] Crie uma atividade ShowActivity, que contenha uma ListView.

### Fase 3
Objetivos:

- [x] Inicialização do servidor remoto
- [x] Adicionado o botão Gravar ao layout da atividade principal
- [x] Associe um evento ao botão Gravar que envie os dados armazenados localmente no aparelho móvel para o servidor remoto.
- [x] Se você fizer somente uma conexão com o servidor remoto, e por essa conexão enviar todos os dados, sua aplicação deverá ser mais eficiente.

### Fase 4
Objetivos:

- [x] Estenda a classe Estate para que ela possua ciência de localização.
- [x] Modifique a atividade que insere novos imóveis no banco de dados (InsertActivity) para que ela guarde as coordenadas do evento em que ocorreu o cadastro.
- [x] Para confirmar que as coordenadas estão sendo capturadas corretamente, modifique essa atividade, para que ela imprima os dados cadastrados no log do Android.
- [x] É possível testar a atividade com localizações diferentes. Para tanto, é necessário que a atividade em foco seja capaz de escutar e atualizar eventos de localização. Em outras palavras, essa atividade precisa ser um LocationListener. Isso não é necessário para esse trabalho, mas pode ser feito.
- [x] O banco de dados da nova aplicação difere do banco de dados implementado nessa última parte do trabalho.

### Fase 5
Objetivos:

- [x] Para tanto, deverá ser criada uma atividade ShowAddressesActivity que implemente a interface OnMapReadyCallback.
- [x] Utilize o marcador ao lado para apontar as coordenadas de cada imóvel.
- [x] Lembre-se de indicar em seu manifesto que a aplicação estará usando a biblioteca de mapas do Google.
- [x] Crie a atividade ShowAddressesActivity que implemente uma MapActivity.
- [x] Essa atividade precisa definir uma sobrecamada (Overlay) que renderiza marcadores sobre o mapa, sendo as coordenadas de cada marcador definidas por cada imóvel cadastrado.
- [x] Inicialmente o mapa deve ser centralizado nas coordenadas do primeiro imóvel cadastrado.
- [x] Adicione um botão de zoom à atividade ShowAddressesActivity de forma tal que o usuário possa observar em maiores detalhes o endereço do imóvel cadastrado.
