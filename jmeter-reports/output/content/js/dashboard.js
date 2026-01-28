/*
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
var showControllersOnly = false;
var seriesFilter = "";
var filtersOnlySampleSeries = true;

/*
 * Add header in statistics table to group metrics by category
 * format
 *
 */
function summaryTableHeader(header) {
    var newRow = header.insertRow(-1);
    newRow.className = "tablesorter-no-sort";
    var cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Requests";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 3;
    cell.innerHTML = "Executions";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 7;
    cell.innerHTML = "Response Times (ms)";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 1;
    cell.innerHTML = "Throughput";
    newRow.appendChild(cell);

    cell = document.createElement('th');
    cell.setAttribute("data-sorter", false);
    cell.colSpan = 2;
    cell.innerHTML = "Network (KB/sec)";
    newRow.appendChild(cell);
}

/*
 * Populates the table identified by id parameter with the specified data and
 * format
 *
 */
function createTable(table, info, formatter, defaultSorts, seriesIndex, headerCreator) {
    var tableRef = table[0];

    // Create header and populate it with data.titles array
    var header = tableRef.createTHead();

    // Call callback is available
    if(headerCreator) {
        headerCreator(header);
    }

    var newRow = header.insertRow(-1);
    for (var index = 0; index < info.titles.length; index++) {
        var cell = document.createElement('th');
        cell.innerHTML = info.titles[index];
        newRow.appendChild(cell);
    }

    var tBody;

    // Create overall body if defined
    if(info.overall){
        tBody = document.createElement('tbody');
        tBody.className = "tablesorter-no-sort";
        tableRef.appendChild(tBody);
        var newRow = tBody.insertRow(-1);
        var data = info.overall.data;
        for(var index=0;index < data.length; index++){
            var cell = newRow.insertCell(-1);
            cell.innerHTML = formatter ? formatter(index, data[index]): data[index];
        }
    }

    // Create regular body
    tBody = document.createElement('tbody');
    tableRef.appendChild(tBody);

    var regexp;
    if(seriesFilter) {
        regexp = new RegExp(seriesFilter, 'i');
    }
    // Populate body with data.items array
    for(var index=0; index < info.items.length; index++){
        var item = info.items[index];
        if((!regexp || filtersOnlySampleSeries && !info.supportsControllersDiscrimination || regexp.test(item.data[seriesIndex]))
                &&
                (!showControllersOnly || !info.supportsControllersDiscrimination || item.isController)){
            if(item.data.length > 0) {
                var newRow = tBody.insertRow(-1);
                for(var col=0; col < item.data.length; col++){
                    var cell = newRow.insertCell(-1);
                    cell.innerHTML = formatter ? formatter(col, item.data[col]) : item.data[col];
                }
            }
        }
    }

    // Add support of columns sort
    table.tablesorter({sortList : defaultSorts});
}

$(document).ready(function() {

    // Customize table sorter default options
    $.extend( $.tablesorter.defaults, {
        theme: 'blue',
        cssInfoBlock: "tablesorter-no-sort",
        widthFixed: true,
        widgets: ['zebra']
    });

    var data = {"OkPercent": 52.38095238095238, "KoPercent": 47.61904761904762};
    var dataset = [
        {
            "label" : "FAIL",
            "data" : data.KoPercent,
            "color" : "#FF6347"
        },
        {
            "label" : "PASS",
            "data" : data.OkPercent,
            "color" : "#9ACD32"
        }];
    $.plot($("#flot-requests-summary"), dataset, {
        series : {
            pie : {
                show : true,
                radius : 1,
                label : {
                    show : true,
                    radius : 3 / 4,
                    formatter : function(label, series) {
                        return '<div style="font-size:8pt;text-align:center;padding:2px;color:white;">'
                            + label
                            + '<br/>'
                            + Math.round10(series.percent, -2)
                            + '%</div>';
                    },
                    background : {
                        opacity : 0.5,
                        color : '#000'
                    }
                }
            }
        },
        legend : {
            show : true
        }
    });

    // Creates APDEX table
    createTable($("#apdexTable"), {"supportsControllersDiscrimination": true, "overall": {"data": [0.5, 500, 1500, "Total"], "isController": false}, "titles": ["Apdex", "T (Toleration threshold)", "F (Frustration threshold)", "Label"], "items": [{"data": [1.0, 500, 1500, "Post-Request-0"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/verify/institution-99"], "isController": false}, {"data": [1.0, 500, 1500, "Post-Request-1"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/institution/mou/get-mous-114"], "isController": false}, {"data": [1.0, 500, 1500, "login/app/institution-93"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/institution/verify-91"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/institution/mou/check-mou-active-113"], "isController": false}, {"data": [0.8, 500, 1500, "login/app/api/institution/login-90"], "isController": false}, {"data": [1.0, 500, 1500, "Post-Request"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/student/getMenu-98"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/student/details-92"], "isController": false}, {"data": [1.0, 500, 1500, "login/app/api/register/academic-payment/get-department-links/Academics-111"], "isController": false}, {"data": [1.0, 500, 1500, "load/app/institution/signin-71"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/institution/institution/get-107"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/menu/get-97"], "isController": false}, {"data": [0.85, 500, 1500, "Get-Request-1"], "isController": false}, {"data": [1.0, 500, 1500, "logout/app/institution/signin-121"], "isController": false}, {"data": [0.0, 500, 1500, "logout/app/api/institution/institution/get-122"], "isController": false}, {"data": [0.85, 500, 1500, "Get-Request"], "isController": false}, {"data": [0.0, 500, 1500, "login/app/api/institution/institution/get-100"], "isController": false}, {"data": [1.0, 500, 1500, "Get-Request-0"], "isController": false}]}, function(index, item){
        switch(index){
            case 0:
                item = item.toFixed(3);
                break;
            case 1:
            case 2:
                item = formatDuration(item);
                break;
        }
        return item;
    }, [[0, 0]], 3);

    // Create statistics table
    createTable($("#statisticsTable"), {"supportsControllersDiscrimination": true, "overall": {"data": ["Total", 210, 100, 47.61904761904762, 108.66190476190476, 30, 785, 54.5, 216.40000000000003, 494.9999999999998, 766.4699999999983, 70.73088582014147, 53.15012946278208, 28.767314331424725], "isController": false}, "titles": ["Label", "#Samples", "FAIL", "Error %", "Average", "Min", "Max", "Median", "90th pct", "95th pct", "99th pct", "Transactions/s", "Received", "Sent"], "items": [{"data": ["Post-Request-0", 10, 0, 0.0, 34.699999999999996, 30, 40, 35.0, 39.7, 40.0, 40.0, 9.72762645914397, 4.303334751945525, 2.6218993190661477], "isController": false}, {"data": ["login/app/api/verify/institution-99", 10, 10, 100.0, 47.7, 31, 111, 43.0, 104.80000000000003, 111.0, 111.0, 9.328358208955223, 2.878673041044776, 3.9991691930970146], "isController": false}, {"data": ["Post-Request-1", 10, 0, 0.0, 58.5, 42, 135, 46.0, 129.3, 135.0, 135.0, 8.865248226950355, 14.570520279255321, 1.5323720079787235], "isController": false}, {"data": ["login/app/api/institution/mou/get-mous-114", 10, 10, 100.0, 56.699999999999996, 30, 130, 38.0, 129.0, 130.0, 130.0, 11.185682326621924, 3.6266079418344517, 5.13405341163311], "isController": false}, {"data": ["login/app/institution-93", 10, 0, 0.0, 73.3, 32, 156, 75.5, 150.60000000000002, 156.0, 156.0, 9.165902841429881, 8.86156622364803, 4.8335815765352885], "isController": false}, {"data": ["login/app/api/institution/verify-91", 10, 10, 100.0, 51.6, 33, 92, 40.5, 91.5, 92.0, 92.0, 8.460236886632826, 2.7429674280879865, 3.684829737732657], "isController": false}, {"data": ["login/app/api/institution/mou/check-mou-active-113", 10, 10, 100.0, 70.7, 30, 132, 54.5, 130.20000000000002, 132.0, 132.0, 10.050251256281408, 3.2584798994974875, 4.691425879396985], "isController": false}, {"data": ["login/app/api/institution/login-90", 10, 0, 0.0, 459.20000000000005, 301, 634, 469.5, 625.1, 634.0, 634.0, 6.447453255963894, 6.296341070277241, 3.8596570760799485], "isController": false}, {"data": ["Post-Request", 10, 0, 0.0, 93.7, 79, 169, 83.5, 163.10000000000002, 169.0, 169.0, 8.591065292096221, 17.92042525773196, 3.80053962628866], "isController": false}, {"data": ["login/app/api/student/getMenu-98", 10, 10, 100.0, 57.7, 30, 126, 46.0, 123.30000000000001, 126.0, 126.0, 9.560229445506693, 3.0996056405353727, 4.070566443594646], "isController": false}, {"data": ["login/app/api/student/details-92", 10, 10, 100.0, 55.9, 31, 124, 44.0, 121.30000000000001, 124.0, 124.0, 8.650519031141869, 2.804660467128028, 4.528006055363322], "isController": false}, {"data": ["login/app/api/register/academic-payment/get-department-links/Academics-111", 10, 0, 0.0, 138.49999999999997, 81, 259, 115.0, 254.20000000000002, 259.0, 259.0, 9.416195856873822, 3.1356667843691146, 4.3862552966101696], "isController": false}, {"data": ["load/app/institution/signin-71", 10, 0, 0.0, 73.4, 46, 181, 49.5, 172.90000000000003, 181.0, 181.0, 7.905138339920948, 7.642663043478262, 3.682374011857708], "isController": false}, {"data": ["login/app/api/institution/institution/get-107", 10, 10, 100.0, 51.99999999999999, 30, 131, 34.5, 128.4, 131.0, 131.0, 9.746588693957115, 3.1600268031189085, 4.264132553606237], "isController": false}, {"data": ["login/app/api/menu/get-97", 10, 10, 100.0, 48.3, 31, 114, 37.0, 108.80000000000001, 114.0, 114.0, 9.233610341643583, 2.9937096029547554, 3.868377770083103], "isController": false}, {"data": ["Get-Request-1", 10, 0, 0.0, 310.1, 109, 657, 183.0, 656.6, 657.0, 657.0, 6.024096385542169, 9.90093185240964, 0.8530214608433735], "isController": false}, {"data": ["logout/app/institution/signin-121", 10, 0, 0.0, 58.4, 32, 107, 41.0, 106.10000000000001, 107.0, 107.0, 12.360939431396785, 11.95051761433869, 6.639176452410383], "isController": false}, {"data": ["logout/app/api/institution/institution/get-122", 10, 10, 100.0, 39.300000000000004, 30, 75, 34.0, 72.9, 75.0, 75.0, 13.642564802182811, 4.423175306957708, 6.301692530695771], "isController": false}, {"data": ["Get-Request", 10, 0, 0.0, 384.19999999999993, 167, 785, 241.5, 784.5, 785.0, 785.0, 5.592841163310962, 11.66631711409396, 1.5839100950782998], "isController": false}, {"data": ["login/app/api/institution/institution/get-100", 10, 10, 100.0, 46.6, 31, 99, 37.0, 95.4, 99.0, 99.0, 9.47867298578199, 3.073163507109005, 4.146919431279621], "isController": false}, {"data": ["Get-Request-0", 10, 0, 0.0, 71.39999999999998, 55, 122, 59.0, 122.0, 122.0, 122.0, 6.097560975609756, 2.697456173780488, 0.863424161585366], "isController": false}]}, function(index, item){
        switch(index){
            // Errors pct
            case 3:
                item = item.toFixed(2) + '%';
                break;
            // Mean
            case 4:
            // Mean
            case 7:
            // Median
            case 8:
            // Percentile 1
            case 9:
            // Percentile 2
            case 10:
            // Percentile 3
            case 11:
            // Throughput
            case 12:
            // Kbytes/s
            case 13:
            // Sent Kbytes/s
                item = item.toFixed(2);
                break;
        }
        return item;
    }, [[0, 0]], 0, summaryTableHeader);

    // Create error table
    createTable($("#errorsTable"), {"supportsControllersDiscrimination": false, "titles": ["Type of error", "Number of errors", "% in errors", "% in all samples"], "items": [{"data": ["401/Unauthorized", 100, 100.0, 47.61904761904762], "isController": false}]}, function(index, item){
        switch(index){
            case 2:
            case 3:
                item = item.toFixed(2) + '%';
                break;
        }
        return item;
    }, [[1, 1]]);

        // Create top5 errors by sampler
    createTable($("#top5ErrorsBySamplerTable"), {"supportsControllersDiscrimination": false, "overall": {"data": ["Total", 210, 100, "401/Unauthorized", 100, "", "", "", "", "", "", "", ""], "isController": false}, "titles": ["Sample", "#Samples", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors", "Error", "#Errors"], "items": [{"data": [], "isController": false}, {"data": ["login/app/api/verify/institution-99", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["login/app/api/institution/mou/get-mous-114", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["login/app/api/institution/verify-91", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["login/app/api/institution/mou/check-mou-active-113", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["login/app/api/student/getMenu-98", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["login/app/api/student/details-92", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["login/app/api/institution/institution/get-107", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": ["login/app/api/menu/get-97", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": [], "isController": false}, {"data": ["logout/app/api/institution/institution/get-122", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}, {"data": ["login/app/api/institution/institution/get-100", 10, 10, "401/Unauthorized", 10, "", "", "", "", "", "", "", ""], "isController": false}, {"data": [], "isController": false}]}, function(index, item){
        return item;
    }, [[0, 0]], 0);

});
