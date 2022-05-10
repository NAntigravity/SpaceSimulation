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

function switchEntityByID(entityType, entity) {
    switch (entityType) {
        case "space.simulation.oop.game.model.celestial.bodies.Asteroid":
            entity.className = "asteroid";
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

async function updateEntity() {
    let response = await fetch('http://localhost:8080/space');
    let entities = await response.json();
    let node;
    if (prevEntities != null) {
        for (let oldEntity of prevEntities) {
            var tileType = entities.map.tiles[oldEntity.coordinateY][oldEntity.coordinateX].tileType;
            var oldNode = map.childNodes[oldEntity.coordinateY].childNodes[oldEntity.coordinateX]
            switchImageByID(oldNode, tileType);
        }
    }
    for (let unit of entities.entities) {
        try {
            node = map.childNodes[unit.coordinateY].childNodes[unit.coordinateX];
        } catch (e) {
            console.log(e.message);
        }
        if (node === undefined) continue;
        switchEntityByID(unit.entityType, node);
    }
    prevEntities = entities.entities;
}

mapCreation().then(() => {
    setInterval(() => updateEntity(), 1000)
});