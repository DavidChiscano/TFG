const RIOT_TOKEN = "RGAPI-a61bb82c-6877-4f5f-a78b-ae990f58b1cc";
var nombre = '0kame';
    const promise = fetch(`https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/${nombre}`, {
            headers: {"X-Riot-Token": RIOT_TOKEN}
        }).then(res => res.json())




