
function TestApi() {
    let btoaStyle = "PGxpbmsgaHJlZj0iaHR0cHM6Ly9jZG4uanNkZWxpdnIubmV0L25wbS9ib290c3RyYXBANS4wLjAtYmV0YTMvZGlzdC9jc3MvYm9vdHN0cmFwLm1pbi5jc3MiIHJlbD0ic3R5bGVzaGVldCIgaW50ZWdyaXR5PSJzaGEzODQtZU9KTVlzZDUzaWkrc2NPL2JKR0ZzaUNaYys1TkRWTjJ5cjgrMFJEcXIwUWwwaCtyUDQ4Y2t4bHBiektnd3JhNiIgY3Jvc3NvcmlnaW49ImFub255bW91cyI+";
    async function download(){
        return fetch('https://cloudlayer-io.p.rapidapi.com/v1/html/image',{
            method: 'POST',
            body: JSON.stringify({
                html: btoaStyle+btoa(document.getElementById('file').outerHTML),
                timeout: 30000
            }),
            headers: {
                "content-type": "application/json",
                "x-rapidapi-key": "3238066a34msh1a6aed6e99441c5p12f422jsnd0def21ebee6",
                "x-rapidapi-host": "cloudlayer-io.p.rapidapi.com"
            }
        }).then(response => response.blob())
            .then(blob => {
                var url = window.URL.createObjectURL(blob);
                var a = document.createElement('a');
                a.href = url;
                a.download = "schedule.jpg";
                document.body.appendChild(a);
                a.click();
                a.remove();
            });

    }

    async function downloadPDF(){
        return fetch('https://cloudlayer-io.p.rapidapi.com/v1/html/pdf',{
            method: 'POST',
            body: JSON.stringify({
                html: btoaStyle+btoa(document.getElementById('file').outerHTML),
                timeout: 30000
            }),
            headers: {
                "content-type": "application/json",
                "x-rapidapi-key": "3238066a34msh1a6aed6e99441c5p12f422jsnd0def21ebee6",
                "x-rapidapi-host": "cloudlayer-io.p.rapidapi.com"
            }
        }).then(response => response.blob())
            .then(blob => {
                var url = window.URL.createObjectURL(blob);
                var a = document.createElement('a');
                a.href = url;
                a.download = "schedule.pdf";
                document.body.appendChild(a);
                a.click();
                a.remove();
            });

    }

    return (
        <div>
            <button className='btn btn-dark' onClick={download}>Download</button>
            <button className='btn btn-dark' onClick={downloadPDF}>Download PDF</button>
            <div id='file' className='bg-primary display-3 text-light border-danger'>
                    <h1>ERLAN</h1>
            </div>
        </div>
    );
}

export default TestApi;