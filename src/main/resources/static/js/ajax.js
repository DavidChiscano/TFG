const RIOT_TOKEN = "?api_key=RGAPI-f496b3dd-0b94-4807-9ee9-7e5caf0acd25";
var nombre = '';
var encryptedSummonerId = '';
//URL's API RIOT'
const urlNombreCuenta = 'https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/';
const urlPerfil = 'https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/';

//BUSCAR INVOCADOR
document.getElementById("btnBuscar").onclick = function() {
	nombre = document.getElementById("buscador").value;
	fetch(urlNombreCuenta + nombre + RIOT_TOKEN)
		.then((resp) => resp.json())
		.then(function(data) {
			let nombreCuenta = document.getElementById("cuentaEncontrada");
			let nivelCuenta = document.getElementById("nivelCuenta");
			nombreCuenta.textContent = data.name;
			nivelCuenta.textContent = data.summonerLevel;
			data.id = encryptedSummonerId;
		})
		.catch(function(error) {
			console.log(error);
		});
}

//VER PERFIL DETALLADO
document.getElementById("ver").onclick = function() {
	console.log(encryptedSummonerId);
	fetch(urlPerfil + encryptedSummonerId + RIOT_TOKEN)
		.then((resp) => resp.json())
		.then(function(data) {
			console.log(data.tier);
		})
		.catch(function(error) {
			console.log(error);
		});


}