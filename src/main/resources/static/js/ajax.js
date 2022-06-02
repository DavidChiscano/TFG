//VARS
const RIOT_TOKEN = "?api_key=RGAPI-0f43b806-2b69-4084-800d-42ada568ce50";
const RIOT_TOKEN2 = "RGAPI-0f43b806-2b69-4084-800d-42ada568ce50";
var nombre = '';
var encryptedSummonerId = '';
var puuid = '';
var token = $("meta[name='_csrf']").attr("content");
var header = $("meta[name='_csrf_header']").attr("content");
//URL's API RIOT'
const urlNombreCuenta = 'https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/';
const urlPerfil = 'https://euw1.api.riotgames.com/lol/league/v4/entries/by-summoner/';
const urlMaestria = 'https://euw1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/';
const urlImgPerfil = 'http://ddragon.leagueoflegends.com/cdn/12.9.1/img/profileicon/';
const urlImgCampeones = 'http://ddragon.leagueoflegends.com/cdn/12.9.1/img/champion/';
const urlItemIcons = 'http://ddragon.leagueoflegends.com/cdn/12.9.1/img/item/';
const urlDatosMaestria = 'https://euw1.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/';
const urlObtenerIdPartida = 'https://europe.api.riotgames.com/lol/match/v5/matches/by-puuid/'
const urlObtenerIdPartida2 = '/ids?count=5&api_key=';
const urlPartidas = 'https://europe.api.riotgames.com/lol/match/v5/matches/'
document.body.style.overflow = 'hidden';
//PERSISTIR CUENTA EN LA BBDD
function persistirCuenta() {
	var csrfToken = $("[name='_csrf']").attr("value");
	fetch('/index/guardarCuenta', {
		headers: {
			"Content-Type": "application/json; charset=utf-8",
			credentials: 'same-origin',
			'X-CSRF-TOKEN': csrfToken
		},
		method: 'POST',
		body: JSON.stringify({ usuario: document.getElementById("cuentaEncontrada").textContent })
	})
		.then(function(response) {
			if (response.ok) {
				return response.json()
			} else {
				throw "Error";
			}
		}).then(data => {
			alert("Cuenta guardada correctamente");
		})
}

document.getElementById("addCuenta").onclick = function() {
	persistirCuenta();
}

//BUSCAR INVOCADOR
document.getElementById("btnBuscar").onclick = function() {
	nombre = document.getElementById("buscador").value;
	fetch(urlNombreCuenta + nombre + RIOT_TOKEN)
		.then(resp => {
			if (resp.ok) {
				return resp.json();
			} else if (resp.status == 404) {
				alert("Jugador no encontrado, compruebe el nombre buscado");
				throw new Error("Jugador no encontrado, compruebe el nombre buscado");
			} else {
				alert("Error, intentelo de nuevo");
				throw new Error("Error, intentelo de nuevo");
			}
		})
		.then(function(data) {
			document.getElementById("datosCuenta").classList.remove("invisible");
			document.getElementById("logo").classList.replace("mt-48", "mt-10");
			let nombreCuenta = document.getElementById("cuentaEncontrada");
			let nivelCuenta = document.getElementById("nivelCuenta");
			let imgPerfil = document.getElementById("imgPerfil");
			imgPerfil.src = urlImgPerfil + data.profileIconId + ".png";
			nombreCuenta.textContent = data.name;
			nivelCuenta.textContent = 'Nivel: ' + data.summonerLevel;
			encryptedSummonerId = data.id;
			puuid = data.puuid;
		})

		.catch(function(error) {
			console.log(error);
		});
}
function limpiarCampos() {

}

//OBTENER LOGO DIVISION
function obtenerLogoDivision(data) {
	if (!Object.keys(data).length) {
		console.log('No hay division');
		document.getElementById("logoDivision").src = '/img/Emblem_Silver.png';
		return;
	}
	else if (data[0].tier == 'SILVER') {
		console.log('ERES PLATA');
		document.getElementById("logoDivision").src = '/img/Emblem_Silver.png';
	}
	else if (data[0].tier == 'GOLD') {
		console.log('ERES ORO');
		document.getElementById("logoDivision").src = '/img/Emblem_Gold.png';
	}
	document.getElementById("tier").textContent = data[0].tier;
	document.getElementById("rank").textContent = data[0].rank;
	document.getElementById("lps").textContent = "LP: " + data[0].leaguePoints;
	document.getElementById("wins").textContent = "Victorias: " + data[0].wins;
	document.getElementById("losses").textContent = "Derrotas: " + data[0].losses;
}

//OBTENER ITEMS PARTIDA 1
function obtenerItemsPartida1(arrayParticipantesF) {
	let nombreCuenta = document.getElementById("cuentaEncontrada").innerText;
	for (let i = 0; i < arrayParticipantesF.length; i++) {
		if (arrayParticipantesF[i].summonerName == nombreCuenta) {
			if (arrayParticipantesF[i].win == true) {
				document.getElementById("resultadoPartida1").textContent = 'Victoria';
			} else {
				document.getElementById("resultadoPartida1").textContent = 'Derrota';
			}
			document.getElementById("avatar1").src = urlImgCampeones + arrayParticipantesF[i].championName + ".png";
			document.getElementById("kda1").textContent = arrayParticipantesF[i].kills +
				' / ' + arrayParticipantesF[i].deaths +
				' / ' + arrayParticipantesF[i].assists;
			if (arrayParticipantesF[i].item0 != 0) {
				document.getElementById("p1item0").src = urlItemIcons + arrayParticipantesF[i].item0 + '.png';
			} else {
				document.getElementById("p1item0").remove();
			}
			if (arrayParticipantesF[i].item1 != 0) {
				document.getElementById("p1item1").src = urlItemIcons + arrayParticipantesF[i].item1 + '.png';
			} else {
				document.getElementById("p1item1").remove();
			}
			if (arrayParticipantesF[i].item2 != 0) {
				document.getElementById("p1item2").src = urlItemIcons + arrayParticipantesF[i].item2 + '.png';
			} else {
				document.getElementById("p1item2").remove();
			}
			if (arrayParticipantesF[i].item3 != 0) {
				document.getElementById("p1item3").src = urlItemIcons + arrayParticipantesF[i].item3 + '.png';
			} else {
				document.getElementById("p1item3").remove();
			}
			if (arrayParticipantesF[i].item4 != 0) {
				document.getElementById("p1item4").src = urlItemIcons + arrayParticipantesF[i].item4 + '.png';
			} else {
				document.getElementById("p1item4").remove();
			}
			if (arrayParticipantesF[i].item5 != 0) {
				document.getElementById("p1item5").src = urlItemIcons + arrayParticipantesF[i].item5 + '.png';
			} else {
				document.getElementById("p1item5").remove();
			}
		}
	}
}


//OBTENER ITEMS PARTIDA 2
function obtenerItemsPartida2(arrayParticipantesF) {
	let nombreCuenta = document.getElementById("cuentaEncontrada").innerText;
	for (let i = 0; i < arrayParticipantesF.length; i++) {
		if (arrayParticipantesF[i].summonerName == nombreCuenta) {
			if (arrayParticipantesF[i].win == true) {
				document.getElementById("resultadoPartida2").textContent = 'Victoria';
			} else {
				document.getElementById("resultadoPartida2").textContent = 'Derrota';
			}
			document.getElementById("avatar2").src = urlImgCampeones + arrayParticipantesF[i].championName + ".png";
			document.getElementById("kda2").textContent = arrayParticipantesF[i].kills +
				' / ' + arrayParticipantesF[i].deaths +
				' / ' + arrayParticipantesF[i].assists;
			if (arrayParticipantesF[i].item0 != 0) {
				document.getElementById("p2item0").src = urlItemIcons + arrayParticipantesF[i].item0 + '.png';
			} else {
				document.getElementById("p2item0").remove();
			}
			if (arrayParticipantesF[i].item1 != 0) {
				document.getElementById("p2item1").src = urlItemIcons + arrayParticipantesF[i].item1 + '.png';
			} else {
				document.getElementById("p2item1").remove();
			}
			if (arrayParticipantesF[i].item2 != 0) {
				document.getElementById("p2item2").src = urlItemIcons + arrayParticipantesF[i].item2 + '.png';
			} else {
				document.getElementById("p2item2").remove();
			}
			if (arrayParticipantesF[i].item3 != 0) {
				document.getElementById("p2item3").src = urlItemIcons + arrayParticipantesF[i].item3 + '.png';
			} else {
				document.getElementById("p2item3").remove();
			}
			if (arrayParticipantesF[i].item4 != 0) {
				document.getElementById("p2item4").src = urlItemIcons + arrayParticipantesF[i].item4 + '.png';
			} else {
				document.getElementById("p2item4").remove();
			}
			if (arrayParticipantesF[i].item5 != 0) {
				document.getElementById("p2item5").src = urlItemIcons + arrayParticipantesF[i].item5 + '.png';
			} else {
				document.getElementById("p2item5").remove();
			}
		}
	}
}

//OBTENER ITEMS PARTIDA 3
function obtenerItemsPartida3(arrayParticipantesF) {
	let nombreCuenta = document.getElementById("cuentaEncontrada").innerText;
	for (let i = 0; i < arrayParticipantesF.length; i++) {
		if (arrayParticipantesF[i].summonerName == nombreCuenta) {
			if (arrayParticipantesF[i].win == true) {
				document.getElementById("resultadoPartida3").textContent = 'Victoria';
			} else {
				document.getElementById("resultadoPartida3").textContent = 'Derrota';
			}
			document.getElementById("avatar3").src = urlImgCampeones + arrayParticipantesF[i].championName + ".png";
			document.getElementById("kda3").textContent = arrayParticipantesF[i].kills +
				' / ' + arrayParticipantesF[i].deaths +
				' / ' + arrayParticipantesF[i].assists;
			if (arrayParticipantesF[i].item0 != 0) {
				document.getElementById("p3item0").src = urlItemIcons + arrayParticipantesF[i].item0 + '.png';
			} else {
				document.getElementById("p3item0").remove();
			}
			if (arrayParticipantesF[i].item1 != 0) {
				document.getElementById("p3item1").src = urlItemIcons + arrayParticipantesF[i].item1 + '.png';
			} else {
				document.getElementById("p3item1").remove();
			}
			if (arrayParticipantesF[i].item2 != 0) {
				document.getElementById("p3item2").src = urlItemIcons + arrayParticipantesF[i].item2 + '.png';
			} else {
				document.getElementById("p3item2").remove();
			}
			if (arrayParticipantesF[i].item3 != 0) {
				document.getElementById("p3item3").src = urlItemIcons + arrayParticipantesF[i].item3 + '.png';
			} else {
				document.getElementById("p3item3").remove();
			}
			if (arrayParticipantesF[i].item4 != 0) {
				document.getElementById("p3item4").src = urlItemIcons + arrayParticipantesF[i].item4 + '.png';
			} else {
				document.getElementById("p3item4").remove();
			}
			if (arrayParticipantesF[i].item5 != 0) {
				document.getElementById("p3item5").src = urlItemIcons + arrayParticipantesF[i].item5 + '.png';
			} else {
				document.getElementById("p3item5").remove();
			}
		}
	}
}

//OBTENER ITEMS PARTIDA 4 
function obtenerItemsPartida4(arrayParticipantesF) {
	let nombreCuenta = document.getElementById("cuentaEncontrada").innerText;
	for (let i = 0; i < arrayParticipantesF.length; i++) {
		if (arrayParticipantesF[i].summonerName == nombreCuenta) {
			if (arrayParticipantesF[i].win == true) {
				document.getElementById("resultadoPartida4").textContent = 'Victoria';
			} else {
				document.getElementById("resultadoPartida4").textContent = 'Derrota';
			}
			document.getElementById("avatar4").src = urlImgCampeones + arrayParticipantesF[i].championName + ".png";
			document.getElementById("kda4").textContent = arrayParticipantesF[i].kills +
				' / ' + arrayParticipantesF[i].deaths +
				' / ' + arrayParticipantesF[i].assists;
			if (arrayParticipantesF[i].item0 != 0 || arrayParticipantesF[i].item1 != 0 || arrayParticipantesF[i].item2 != 0 || arrayParticipantesF[i].item3 != 0 || arrayParticipantesF[i].item4 != 0 || arrayParticipantesF[i].item5 != 0) {
				console.log('tiene items');
				document.getElementById("item0").src = urlItemIcons + arrayParticipantesF[i].item0 + '.png';
				document.getElementById("item1").src = urlItemIcons + arrayParticipantesF[i].item1 + '.png';
				document.getElementById("item2").src = urlItemIcons + arrayParticipantesF[i].item2 + '.png';
				document.getElementById("item3").src = urlItemIcons + arrayParticipantesF[i].item3 + '.png';
				document.getElementById("item4").src = urlItemIcons + arrayParticipantesF[i].item4 + '.png';
				document.getElementById("item5").src = urlItemIcons + arrayParticipantesF[i].item5 + '.png';
			}
		}
	}
}



//CARGAR JSON LOCAL CAMPEONES
function loadJSON(callback) {
	var xobj = new XMLHttpRequest();
	xobj.overrideMimeType("application/json");
	xobj.open('GET', 'js/champions.json', true);
	xobj.onreadystatechange = function() {
		if (xobj.readyState == 4 && xobj.status == "200") {
			callback(xobj.responseText);
		}
	}
	xobj.send(null);
}
//MOSTRAR DATOS EN EL DOM
function mostrarElementos() {
	document.getElementById("infoLiga").classList.remove("invisible");
	document.getElementById("masJugados").classList.remove("invisible");
	document.getElementById("spanMasJugados").classList.remove("invisible");
	document.getElementById("spanUltimasPartidas").classList.remove("invisible");
	document.getElementById("ultimasPartidas").classList.remove("invisible");
	document.body.style.overflow = 'visible';
}
//VER PERFIL DETALLADO
document.getElementById("ver").onclick = function() {
	fetch(urlPerfil + encryptedSummonerId + RIOT_TOKEN)
		.then((resp) => {
			if (resp.ok) {
				return resp.json();
			} else if (resp.status == 404) {
				alert("Jugador no encontrado, compruebe el nombre buscado");
				throw new Error("Jugador no encontrado, compruebe el nombre buscado");
			} else {
				alert("Error, intentelo de nuevo");
				throw new Error("Error, intentelo de nuevo");
			}
		})
		.then(function(data) {
			obtenerLogoDivision(data);
			mostrarElementos();
			//OBTENER MAESTRIA PERSONAJES		
			fetch(urlMaestria + encryptedSummonerId + RIOT_TOKEN)
				.then((resp) => {
					if (resp.ok) {
						return resp.json();
					} else if (resp.status == 404) {
						alert("Jugador no encontrado, compruebe el nombre buscado");
						throw new Error("Jugador no encontrado, compruebe el nombre buscado");
					} else {
						alert("Error, intentelo de nuevo");
						throw new Error("Error, intentelo de nuevo");
					}
				})
				.then(function(dataMastery) {
					if (!Object.keys(data).length) {
						console.log('No existen datos de maestria');
					} else {
						loadJSON(function(response) { //Cargar el fichero json local
							//Convertir a json la respuesta
							var jsonresponse = JSON.parse(response);
							var championsId = [];
							championsId.push(jsonresponse.data);
							//Almaceno el id de los personajes con mas maestria de la cuenta
							var ids = [];
							for (let i = 0; i < 3; i++) {
								console.log(dataMastery[i]);
								ids.push(dataMastery[i].championId);
							}
							//Mostrar en pantalla
							document.getElementById("puntosPj1").textContent = 'Puntos: ' + dataMastery[0].championPoints;
							document.getElementById("puntosPj2").textContent = 'Puntos: ' + dataMastery[1].championPoints;
							document.getElementById("puntosPj3").textContent = 'Puntos: ' + dataMastery[2].championPoints;
							//Array con la info de los campeones mapeado a mi formato para usarlo
							var arrayMapped = championsId.map(x => Object.values(x)).flat();
							//Recorrer el array para obtener los 3 nombres de los campeones con mas maestria de la cuenta
							var pj1 = "";
							var pj2 = "";
							var pj3 = "";

							var tagsPj1 = []; //Tipo de personaje1
							var tagsPj2 = []; //Tipo de personaje2
							var tagsPj3 = []; //Tipo de personaje3
							for (let i = 0; i < arrayMapped.length; i++) {
								if (arrayMapped[i].key == ids[0]) {
									console.log(arrayMapped[i].name);
									tagsPj1.push(arrayMapped[i].tags);
									pj1 = arrayMapped[i].name;
									document.getElementById("nombrePj1").textContent = arrayMapped[i].name;
									document.getElementById("lorePj1").textContent = arrayMapped[i].title;
									for (let x = 0; x < tagsPj1.length; x++) {
										document.getElementById("tagsPj1").textContent = tagsPj1[x];
									}
								} else if (arrayMapped[i].key == ids[1]) {
									console.log(arrayMapped[i].name);
									tagsPj2.push(arrayMapped[i].tags);
									pj2 = arrayMapped[i].name;
									document.getElementById("nombrePj2").textContent = arrayMapped[i].name;
									document.getElementById("lorePj2").textContent = arrayMapped[i].title;
									for (let x = 0; x < tagsPj2.length; x++) {
										document.getElementById("tagsPj2").textContent = tagsPj2[x];
									}
								} else if (arrayMapped[i].key == ids[2]) {
									console.log(arrayMapped[i].name);
									tagsPj3.push(arrayMapped[i].tags);
									document.getElementById("nombrePj3").textContent = arrayMapped[i].name;
									document.getElementById("lorePj3").textContent = arrayMapped[i].title;
									for (let x = 0; x < tagsPj3.length; x++) {
										document.getElementById("tagsPj3").textContent = tagsPj3[x];
									}
									pj3 = arrayMapped[i].name;
								}
							}
							console.log(arrayMapped);

							//Check si es este personaje para quitar el caracter del nombre y cargar los datos correctamente
							if (pj1 == "Kai'Sa") {
								pj1 = "Kaisa";
							} else if (pj2 == "Kai'Sa") {
								pj2 = "Kaisa"
							} else if (pj3 == "Kai'Sa") {
								pj3 = "Kaisa";
							}
							document.getElementById("masJugado1").src = urlImgCampeones + pj1.split(' ').join('').split("'").join('') + ".png";
							document.getElementById("masJugado2").src = urlImgCampeones + pj2.split(' ').join('').split("'").join('') + ".png";
							document.getElementById("masJugado3").src = urlImgCampeones + pj3.split(' ').join('').split("'").join('') + ".png";
						});

					}

				})
			fetch(urlObtenerIdPartida + puuid + urlObtenerIdPartida2 + RIOT_TOKEN2)
				.then(resp => {
					if (resp.ok) {
						return resp.json();
					} else if (resp.status == 404) {
						alert("Jugador no encontrado, compruebe el nombre buscado");
						throw new Error("Jugador no encontrado, compruebe el nombre buscado");
					} else {
						alert("Error, intentelo de nuevo");
						throw new Error("Error, intentelo de nuevo");
					}
				})
				.then(function(data) {
					var idsPartidas = [];
					idsPartidas.push(data);
					console.log(idsPartidas);
					fetch(urlPartidas + idsPartidas[0][0] + RIOT_TOKEN)
						.then(resp => {
							if (resp.ok) {
								return resp.json();
							} else {
								alert("Error, intentelo de nuevo");
								throw new Error("Error, intentelo de nuevo");
							}
						})
						.then(function(data) {

							var arrayParticipantes = [];
							arrayParticipantes.push(data.info.participants);
							var arrayParticipantesF = arrayParticipantes.map(x => Object.values(x)).flat();
							console.log(arrayParticipantesF);
							obtenerItemsPartida1(arrayParticipantesF);
							document.getElementById("modoPartida1").textContent = data.info.gameMode;

						})
					fetch(urlPartidas + idsPartidas[0][1] + RIOT_TOKEN)
						.then(resp => {
							if (resp.ok) {
								return resp.json();
							} else {
								alert("Error, intentelo de nuevo");
								throw new Error("Error, intentelo de nuevo");
							}
						})
						.then(function(data) {
							var arrayParticipantes = [];
							arrayParticipantes.push(data.info.participants);
							var arrayParticipantesF = arrayParticipantes.map(x => Object.values(x)).flat();
							console.log(arrayParticipantesF);
							obtenerItemsPartida2(arrayParticipantesF);
							document.getElementById("modoPartida2").textContent = data.info.gameMode;
						})
					fetch(urlPartidas + idsPartidas[0][2] + RIOT_TOKEN)
						.then(resp => {
							if (resp.ok) {
								return resp.json();
							} else {
								alert("Error, intentelo de nuevo");
								throw new Error("Error, intentelo de nuevo");
							}
						})
						.then(function(data) {
							var arrayParticipantes = [];
							arrayParticipantes.push(data.info.participants);
							var arrayParticipantesF = arrayParticipantes.map(x => Object.values(x)).flat();

							console.log(arrayParticipantesF);

							obtenerItemsPartida3(arrayParticipantesF);
							document.getElementById("modoPartida3").textContent = data.info.gameMode;

						})
					fetch(urlPartidas + idsPartidas[0][3] + RIOT_TOKEN)
						.then(resp => {
							if (resp.ok) {
								return resp.json();
							} else {
								alert("Error, intentelo de nuevo");
								throw new Error("Error, intentelo de nuevo");
							}
						})
						.then(function(data) {
							var arrayParticipantes = [];
							arrayParticipantes.push(data.info.participants);
							var arrayParticipantesF = arrayParticipantes.map(x => Object.values(x)).flat();
							console.log(arrayParticipantesF);
							console.log(data);
							obtenerItemsPartida4(arrayParticipantesF);
							document.getElementById("modoPartida4").textContent = data.info.gameMode;
						})
				})
		})
		.catch(function(error) {
			console.log(error);
		});

}