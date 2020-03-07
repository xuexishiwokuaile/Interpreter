
function DrawTree() {

    var width = 500, height = 500;

    var tree = d3.layout.tree()

        .size([width, height-200]) //设定尺寸，即转换后的各个节点的坐标在哪一个范围内

        .separation(function(a, b) { return (a.parent == b.parent ? 1 : 2) / a.depth; });//设置节点之间的间隔；

    var diagonal = d3.svg.diagonal()

        .projection(function(d) { return [d.y, d.x]; });



    var svg = d3.select("div.tree").append("svg") //将svg元素插入到class为tree的div标签之前即editor2里面

        .attr("width", width)

        .attr("height", height)

        .append("g")

        .attr("transform", "translate(40,0)");







    d3.json("grammar.json", function(error, root) {



        var nodes = tree.nodes(root);

        var links = tree.links(nodes);



        console.log(nodes);

        console.log(links);



        var link = svg.selectAll(".link")

            .data(links)

            .enter()

            .append("path")

            .attr("class", "link")

            .attr("d", diagonal);



        var node = svg.selectAll(".node")

            .data(nodes)

            .enter()

            .append("g")

            .attr("class", "node")

            .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })



        node.append("circle")

            .attr("r", 4.5);



        node.append("text")

            .attr("dx", function(d) { return d.children ? -8 : 8; })

            .attr("dy", 3)

            .style("text-anchor", function(d) { return d.children ? "end" : "start"; })

            .text(function(d) { return d.name; });


    });

}




