var configTestArray = {
    method: 'GET',
    data: null,
    url: 'http://localhost:8080/sudoku/TestArrayList',
    success: function (res) {
        alert('success');
        var response = JSON.parse(res);
        console.log(res);
        console.log(response);
        console.log(response[0]);
    }
};

var ajax = function (config) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function() {
        if (this.status === 200) {
            config.success(xhr.response);
        }
    };
    xhr.open(config.method, config.url, true);
    xhr.send(config.data);
};

ajax(configTestArray);