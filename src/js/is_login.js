if (typeof(Storage)!== "undefined") 
{
	// Web storage is supported
	if (sessionStorage.userSession == null)
	{
		window.location = "index.html";
	}
}
else 
{
	// Web storage is NOT supported
}