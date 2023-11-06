findAll()
function findAll() {

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/transactionType",
        success: function (data) {
            console.log(data)
            displayTable(data)
        }
    })
}

function displayTable(value) {
    // console.log(value)
    let content=`<h1>List TransactionType</h1>
    <div style="">
    <span>
    <h3>Danh sách loại giao dịch</h3>
</span>
    <button onclick="displayFormCreate()" style="margin-left: 1060px ; margin-bottom: 5px">Thêm loại giao dịch</button>
    </div>
    <table border="1">
    <tr>
        <th style="text-align: center ; border: 1px solid black">#</th>
        <th style="text-align: center ; border: 1px solid black">Loại giao dịch</th>
        <th colspan="3" style="text-align: center ; border: 1px solid black">Action</th>
    </tr>`
    for (let i = 0; i < value.length; i++) {
        content +=`<tr class="item">
                   <td>${value[i].id}</td>
                   <td>${value[i].name}</td>
        
                   <td><button onclick="views(${value[i].id})">Views</button></td>
                   <td><button onclick="displayFormUpdate(${value[i].id})">Update</button></td>
                   <td><button onclick="deleteTransactionType(${value[i].id})">Delete</button></td>
                   </tr>`
    }
    content += `</table>`
    document.getElementById("list").style.display = "block"
    document.getElementById("list").innerHTML =content
    document.getElementById("create").style.display = "none"
    document.getElementById("update").style.display = "none"
    document.getElementById("views").style.display = "none"
}

function views(id) {
    $.ajax({
        url: `http://localhost:8080/api/transactionType/${id}`,
        type: "GET",
        success: function (TransactionType) {
            let content = `
             <div style="border: 1px solid black ;width: 555px ; margin-left: 600px">
             <div class="views-container" style="display: block">
            <h1>Loại giao dịch  ${TransactionType.name} </h1>
            <button onclick="findAll()" style="height: 50px ; margin-left: 320px">Xem danh sách</button>
            </div>
            <div class="views-container-inner" >
            <div style="margin-top: 25px">Tên: ${TransactionType.name}</div>
            
            </div>
            <div class="views-container-inner-btn" >    
            <td><button onclick="displayFormUpdate(${TransactionType.id})" style="margin-top: 25px">Update</button></td>
            <td><button onclick="deleteTransactionType(${TransactionType.id})" style="margin-top: 25px">Delete</button></td>
            </div>   
            </div>`

            document.getElementById("list").style.display = "none"
            document.getElementById("views").style.display = "block"
            document.getElementById("views").innerHTML = content
            document.getElementById("create").style.display = "none"
            document.getElementById("update").style.display = "none"

        }
    })

}

function displayFormCreate() {
    document.getElementById("create").style.display = "block"
    document.getElementById("list").style.display = "none"
    document.getElementById("page").style.display = "none"
    document.getElementById("views").style.display = "none"

}

function create(){
    let name = $("#name").val()


    let transactiontype = {
        name: name,
    }


    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "http://localhost:8080/api/transactionType",
        type: "POST",
        data: JSON.stringify(transactiontype),
        success: function (){
            findAll()
            document.getElementById("create").style.display="none"
        }
    })
}

function deleteTransactionType(id){
    if(confirm("are you sure ?")){
        $.ajax({
            url: `http://localhost:8080/api/transactionType/${id}`,
            type: "DELETE",
            success: findAll
        })
    }
}

let idUpdate;
function displayFormUpdate(id) {
    $.ajax({
        url: `http://localhost:8080/api/transactionType/${id}`,
        type: "GET",
        success: function (TransactionType) {
            idUpdate = TransactionType.id
            document.getElementById("name-u").value = TransactionType.name
        }
    })
    document.getElementById("update").style.display = "block"
    document.getElementById("list").style.display = "none"
    document.getElementById("page").style.display = "none"
    document.getElementById("views").style.display = "none"
}

function update(){

    let name = $("#name-u").val()


    let transactiontype = {
        name: name,
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: `http://localhost:8080/api/transactionType/${idUpdate}`,
        type: "PUT",
        data: JSON.stringify(transactiontype),
        success: function (){
            findAll()
            document.getElementById("update").style.display = "none"
        },
        error: function () {
            alert("Transactiontype not exists!")
        }
    })
}







