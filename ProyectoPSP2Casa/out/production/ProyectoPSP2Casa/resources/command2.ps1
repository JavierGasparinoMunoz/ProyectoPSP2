$url="https://www.pccomponentes.com/buscar/?query=auriculares+bluetooth"
$result = Invoke-WebRequest $url
$result.AllElements | Where Class -eq "tarjeta-articulo_precio-actual" | %{$_.innerText} | Out-File C:\Users\javie\IdeaProjects\ProyectoPSP2Casa\src\resources\cosa2.txt