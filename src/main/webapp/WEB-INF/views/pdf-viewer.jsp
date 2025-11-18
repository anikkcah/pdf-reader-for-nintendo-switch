<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${pdf.name}</title>
    <style>
        body {
            font-family: Georgia, serif;
            line-height: 1.6;
            background-color: #fdf6e3;
            padding: 2em;
            color: #333;
        }
        .page {
            max-width: 800px;
            margin: auto;
        }
        #pdf-canvas {
            width: 100%;
            border: 1px solid #ccc;
            margin-top: 1em;
        }
        .nav {
            margin-top: 2em;
            text-align: center;
        }
        button {
            margin: 0 10px;
            padding: 0.5em 1em;
            font-size: 1em;
        }
    </style>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/pdf.js/3.11.174/pdf.min.js"></script>
</head>
<body>
    <div class="page">
        <h1>${pdf.name}</h1>

        <!-- PDF.js Canvas -->
        <canvas id="pdf-canvas"></canvas>

        <!-- Navigation Controls -->
        <div class="nav">
            <button onclick="prevPage()">← Previous</button>
            <button onclick="nextPage()">Next →</button>
            <p>Page <span id="page-num">1</span> of <span id="page-count">?</span></p>
        </div>

        <!-- Fallback for older browsers -->
        <noscript>
            <p>Your browser does not support JavaScript or inline PDF viewing.</p>
            <a href="${pdf.path}" target="_blank">Download PDF</a>
        </noscript>
    </div>

    <script>
        const url = "${pdf.path}";
        let pdfDoc = null,
            pageNum = 1,
            canvas = document.getElementById('pdf-canvas'),
            ctx = canvas.getContext('2d');

        pdfjsLib.getDocument(url).promise.then(function(pdf) {
            pdfDoc = pdf;
            document.getElementById('page-count').textContent = pdf.numPages;
            renderPage(pageNum);
        });

    
        
        function renderPage(num) {
        	
            pdfDoc.getPage(num).then(function(page) {
                const viewport = page.getViewport({ scale: 1.0 }); // increase to 1.5 for more more resolution, but it affects the loading time 
                canvas.height = viewport.height;
                canvas.width = viewport.width;

                const renderContext = {
                    canvasContext: ctx,
                    viewport: viewport
                };
                ctx.clearRect(0, 0, canvas.width, canvas.height);
                page.render(renderContext).promise.then(function() {
                    document.getElementById('page-num').textContent = num;
                });
            });
        }

        function nextPage() {
            if (pageNum < pdfDoc.numPages) {
                pageNum++;
                renderPage(pageNum);
            }
        }

        function prevPage() {
            if (pageNum > 1) {
                pageNum--;
                renderPage(pageNum);
            }
        }
    </script>
</body>
</html>