const url = "https://adventuremap-app.azurewebsites.net/AdventureMap";
var dataCallBack = [];

/**
 * Funcion generada para pedir el arreglo de monstruos definido
 * en el backend 
 * @param {Function} callback 
 * @returns 
 */
function getMonstruos(callback){
    $.get(url1+"/monstruos", function(data){
        dataCallBack = data;
    });
    console.log("ENTR AL CLIENT REQUEST");
    return callback(dataCallBack);
}