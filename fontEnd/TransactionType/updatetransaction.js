let str = "";
function showFormUpdateTran(id) {
    axios.get("http://localhost:8080/api/transactionType/" +id).then(res => {
        let data = res.data;
        str = `
         <input type="text" id="id" value="${data.id}"> 
        <input type="text" id="name" value="${data.name}" >
        <button onclick="updateTran()">Update</button>
        `
        document.getElementById('body').innerHTML = str;
    })

}
function updateTran() {
    let data = {
        id: document.getElementById('id').value,
        name: document.getElementById('name').value,
    }
    console.log(data)
    axios.put('http://localhost:8080/api/transactionType/'+data.id, data).then((res) =>{

        showAllTransaction()
    })

}

