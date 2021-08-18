const fill = (listCartas,listTypes) => {
   console.log(listTypes);
    console.log(listCartas);
    let table = "";
    let options = "";

    if(listCartas.length > 0){
        for(let i = 0; i < listCartas.length; i++) {
            table += `
			<tr>
				<td>${ i + 1 }</td>
				<td>${listCartas[i].nombre}</td>
				<td>${listCartas[i].fecha}</td>
				<td>${listCartas[i].status ? "Activo" : "Inactivo"}</td>
				<td>
					<button id="btn-modificar" type="button" class="btn btn-primary">Modificar</button>
					<button id="btn-delete" type="button" class="btn btn-danger">Eliminar</button>
				</td>
			</tr>
			`;
        }
        for(let i = 0; i < listTypes.length; i++) {
            options += `
            <option value="${listTypes[i].idType}">${listTypes[i].type}</option>
            `;
        }
    }else{
        table = `
		<tr class="text-center">
			<td colspan="5">No hay registros para mostrar</td>
		</tr>
		`;
    }
    $(`#container > tbody`).html(table);
    $(`#type_id`).html(options);
};

const findAll = () => {
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    $.ajax({
        type: 'GET',
        url: contextPath + '/readCartas',
        data: { }
    }).done(function(res){
        console.log(res);
        fill(res.listCartas,res.listTypes);
    });
};

findAll();

$('#btn-registrar').on('click',function(e){
    const contextPath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var nam,fech,cate;
    var data=new FormData();
    data.append("nombre",document.getElementById("nombre").value);
    data.append("fecha",document.getElementById("fecha").value);
    data.append("idType",document.getElementById("type_id").value);
    data.append("action","create");

    $.ajax({
        url : contextPath + "/createCartas" ,
        type: "POST",
        data:data,
        processData: false,
        contentType: false,
        success:function(resp){
       alert(resp.message);
    },
    error: function (jqXHR,estado,error){
        alert("Error")
    }

    })
})



