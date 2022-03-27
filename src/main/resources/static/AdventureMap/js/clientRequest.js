const url = 'http://localhost:8080/AdventureMap';
var dataCallBack = [];

/**
 * Funcion generada para pedir el arreglo de monstruos definido
 * en el backend 
 * @param {Function} callback 
 * @returns 
 */
function getMonstruos(callback){
    $.get(url+"/monstruos", function(data){
        dataCallBack = data;
    });
    console.log("ENTR AL CLIENT REQUEST");
    return callback(dataCallBack);
}