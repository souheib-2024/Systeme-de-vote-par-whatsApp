am5.ready(function() {
                    
    var root = am5.Root.new("chartdiv");
  
    root.setThemes([
      am5themes_Animated.new(root)
    ]);
  
    // 🔽 Exemple de données FICTIVES (à remplacer par le backend plus tard)
    var data = [
        {
            name: "Atelier A",
            steps: 120,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135715.png"
            }
        },
        {
            name: "Conférence B",
            steps: 98,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135789.png"
            }
        },
        {
            name: "Workshop C",
            steps: 75,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135779.png"
            }
        },
        {
            name: "Débat D",
            steps: 63,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135784.png"
            }
        },
        {
            name: "Stand E",
            steps: 45,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135717.png"
            }
        },
        {
            name: "Présentation F",
            steps: 130,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135781.png"
            }
        },
        {
            name: "Hackathon G",
            steps: 110,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135768.png"
            }
        },
        {
            name: "Formation H",
            steps: 90,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135755.png"
            }
        },
        {
            name: "Pitch I",
            steps: 66,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135790.png"
            }
        },
        {
            name: "Forum J",
            steps: 55,
            pictureSettings: {
            src: "https://cdn-icons-png.flaticon.com/512/3135/3135712.png"
            }
        }
        ];

  
    /*
    // 🔄 Plus tard : décommenter pour appeler le backend
    */
  
    var chart = root.container.children.push(
        am5xy.XYChart.new(root, {
            panX: false,
            panY: false,
            wheelX: "none",
            wheelY: "none",
            paddingBottom: 50,
            paddingTop: 40
        })
    );
  
    var xRenderer = am5xy.AxisRendererX.new(root, {
        minorGridEnabled: true,
        minGridDistance: 60
    });
    xRenderer.grid.template.set("visible", false);
  
    var xAxis = chart.xAxes.push(
        am5xy.CategoryAxis.new(root, {
            paddingTop: 40,
            categoryField: "name",
            renderer: xRenderer
        })
    );
  
    var yRenderer = am5xy.AxisRendererY.new(root, {});
    yRenderer.grid.template.set("strokeDasharray", [3]);
  
    var yAxis = chart.yAxes.push(
        am5xy.ValueAxis.new(root, {
            min: 0,
            renderer: yRenderer
        })
    );
  
    var series = chart.series.push(
      am5xy.ColumnSeries.new(root, {
        name: "Résultats",
        xAxis: xAxis,
        yAxis: yAxis,
        valueYField: "steps",
        categoryXField: "name",
        sequencedInterpolation: true,
        calculateAggregates: true,
        maskBullets: false,
        tooltip: am5.Tooltip.new(root, {
            dy: -30,
            pointerOrientation: "vertical",
            labelText: "{valueY}"
        })
      })
    );
  
    series.columns.template.setAll({
        strokeOpacity: 0,
        cornerRadiusBR: 10,
        cornerRadiusTR: 10,
        maxWidth: 50,
        fillOpacity: 0.8
    });
  
    var currentlyHovered;
  
    series.columns.template.events.on("pointerover", function (e) {
        handleHover(e.target.dataItem);
    });
  
    series.columns.template.events.on("pointerout", function () {
        handleOut();
    });
  
    function handleHover(dataItem) {
        if (dataItem && currentlyHovered != dataItem) {
            handleOut();
            currentlyHovered = dataItem;
            var bullet = dataItem.bullets[0];
            bullet.animate({
            key: "locationY",
            to: 1,
            duration: 600,
            easing: am5.ease.out(am5.ease.cubic)
            });
        }
    }
  
    function handleOut() {
        if (currentlyHovered) {
            var bullet = currentlyHovered.bullets[0];
            bullet.animate({
            key: "locationY",
            to: 0,
            duration: 600,
            easing: am5.ease.out(am5.ease.cubic)
            });
        }
    }
  
    var circleTemplate = am5.Template.new({});
  
    series.bullets.push(function (root, series, dataItem) {
        var bulletContainer = am5.Container.new(root, {});
        var circle = bulletContainer.children.push(
            am5.Circle.new(root, {
            radius: 34
            }, circleTemplate)
        );
    
        var maskCircle = bulletContainer.children.push(
            am5.Circle.new(root, { radius: 27 })
        );
    
        var imageContainer = bulletContainer.children.push(
            am5.Container.new(root, {
            mask: maskCircle
            })
        );
    
        imageContainer.children.push(
            am5.Picture.new(root, {
            templateField: "pictureSettings",
            centerX: am5.p50,
            centerY: am5.p50,
            width: 60,
            height: 60
            })
        );
    
        return am5.Bullet.new(root, {
            locationY: 0,
            sprite: bulletContainer
        });
    });
  
    series.set("heatRules", [
        {
            dataField: "valueY",
            min: am5.color(0xe5dc36),
            max: am5.color(0x5faa46),
            target: series.columns.template,
            key: "fill"
        },
        {
            dataField: "valueY",
            min: am5.color(0xe5dc36),
            max: am5.color(0x5faa46),
            target: circleTemplate,
            key: "fill"
        }
    ]);
  
    // ⬇️ Affectation des données fictives
    series.data.setAll(data);
    xAxis.data.setAll(data);
  
    var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
    cursor.lineX.set("visible", false);
    cursor.lineY.set("visible", false);
  
    cursor.events.on("cursormoved", function () {
        var dataItem = series.get("tooltip").dataItem;
        if (dataItem) {
            handleHover(dataItem);
        } else {
            handleOut();
        }
    });
  
    series.appear();
    chart.appear(1000, 100);
  
  }); // end am5.ready()