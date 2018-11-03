# fivestars-api
API Rest de cadastro de hóspedes e registro de check-in

<h3>Cadastro de hóspedes</h3>

<p>Para cadastrar um hóspede, faça uma requisição <strong>POST</strong> em </strong>/hospedes</strong> com o seguinte corpo JSON:</p>
<p>{ "nome" : "Nome do Hóspede", "documento" : "CPF em formato válido", "telefone" : "(99)99999-9999" }</p>

<h3>Consulta de um único hóspede</h3>

<p>Para obter os dados de um hóspede, faça uma requisição <strong>GET</strong> em </strong>/hospedes/{id}</strong>, em que "{id}" representa o código do hóspede desejado (ex.: /hospedes/5)</p>
