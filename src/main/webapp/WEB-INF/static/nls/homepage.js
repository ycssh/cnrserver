

$(function () {
        payLine();
        accountLine();
        accountPie();
        payPie();
    }
)
function accountPie() {
    $.post(basePath+"homepage/accountpie", {}, function (data) {
        var totalaccount = 0;
        for (var i = 0; i < data.length; i++) {
            totalaccount += parseInt(data[i].value);
        }
        $("#totalaccount").text(totalaccount+"人");
        var option = {
            title: {
                text: '用户注册来源',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series: [
                {
                    name: '注册来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            },
                            labelLine: {show: true}
                        }
                    }
                }
            ]
        };

        var accountPie = echarts.init(document.getElementById('accountPie'));
        accountPie.setOption(option);
    }, "json");
}
function payPie() {
    $.post(basePath+"homepage/payPie", {}, function (data) {
        var totalpay = 0;
        for (var i = 0; i < data.length; i++) {
            console.info(data[i].value)
            totalpay += parseFloat(data[i].value);
        }
        $("#totalPay").text(totalpay.toFixed(2)+"元");
        var option = {
            title: {
                text: '充值来源',
                x: 'center'
            },
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            series: [
                {
                    name: '充值来源',
                    type: 'pie',
                    radius: '55%',
                    center: ['50%', '60%'],
                    data: data,
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            },
                            labelLine: {show: true}
                        }
                    }
                }
            ]
        };

        var payPie = echarts.init(document.getElementById('payPie'));
        payPie.setOption(option);
    }, "json");
}

function payLine() {
    $.post(basePath+"homepage/payline", {}, function (data) {
        optionc = {
            title: {
                text: '近半月充值情况(元)'
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: ['14天前', '13天前', '12天前', '11天前', '10天前', '9天前', '8天前',
                        '7', '6天前', '5天前', '4天前', '3天前', '前天', '昨天', '今天']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    type: 'line',
                    smooth: true,
                    itemStyle: {normal: {label: {show: true}}},
                    data: data
                }
            ]
        };

        var payLine = echarts.init(document.getElementById('payLine'));
        payLine.setOption(optionc);
    }, "json");
}
function accountLine() {
    $.post(basePath+"homepage/accountline", {}, function (data) {
        optionc = {
            title: {
                text: '近半月注册用户数量(人)'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            tooltip: {
                trigger: 'axis'
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: ['14天前', '13天前', '12天前', '11天前', '10天前', '9天前', '8天前',
                        '7', '6天前', '5天前', '4天前', '3天前', '前天', '昨天', '今天']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    type: 'line',
                    smooth: true,
                    itemStyle: {normal: {label: {show: true}}},
                    data: data
                }
            ]
        };

        var accountLine = echarts.init(document.getElementById('accountLine'));
        accountLine.setOption(optionc);
    }, "json");
}