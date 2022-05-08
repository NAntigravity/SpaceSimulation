function switchImageByID(tile, ID) {
    switch (ID) {
        case "space.simulation.oop.game.model.Tile":
            tile.className = "space";
            break;
        case "space.simulation.oop.game.model.celestial.bodies.Star":
            tile.className = "star";
            break;
        case "space.simulation.oop.game.model.celestial.bodies.Planet":
            tile.className = "planet";
            break;
    }
}

let tiles;
let w;
let h;
let map;
let prevEntities = null;

async function mapCreation() {
    let response = await fetch('http://localhost:8080/space');
    let json = await response.json();

    w = json.map.width;
    h = json.map.height;
    tiles = json.map.tiles;

    map = document.getElementById("field");

    for (let i = 0; i < h; i++) {
        let col = document.createElement("div");
        col.classList.add("row");
        for (let j = 0; j < w; j++) {
            let img = document.createElement("img");
            switchImageByID(img, tiles[i][j].tileType);

            img.setAttribute('X', j);
            img.setAttribute('Y', i);

            col.appendChild(img);
        }
        map.appendChild(col);
    }
}

mapCreation().then(()=>{
    setInterval(()=>{console.log("I'm okay=(")},1000)
});