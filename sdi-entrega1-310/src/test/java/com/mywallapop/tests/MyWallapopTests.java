package com.mywallapop.tests;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.List;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.mywallapop.services.InsertSampleDataService;
import com.mywallapop.services.OffersService;
import com.mywallapop.services.UsersService;
import com.mywallapop.tests.pageobjects.PO_HomeView;
import com.mywallapop.tests.pageobjects.PO_LoginView;
import com.mywallapop.tests.pageobjects.PO_NewOfferView;
import com.mywallapop.tests.pageobjects.PO_RegisterView;
import com.mywallapop.tests.pageobjects.PO_View;
import com.mywallapop.tests.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyWallapopTests {

//	static String URLlocal = "http://localhost:8090";
//	static String URLremota = "http://urlsdispring:xxxx";
//	static String URL = URLremota; // Se va a probar con la URL remota, sino URL=URLlocal

	// Antes de cada prueba se navega al URL home de la aplicaciónn

	@Autowired
	private InsertSampleDataService init;

	@Autowired
	private UsersService userService;

	

	@Before
	public void setUp() throws ParseException {
		init.deleteBBDD();
		init.init();
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() throws ParseException {

		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {

	}

	// Al finalizar la última prueba
	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	static String PathFirefox65 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver024 = "C:\\Users\\Niobe\\Downloads\\PL-SDI-Sesión5-material\\PL-SDI-Sesión5-material\\geckodriver024win64.exe";
	static WebDriver driver = getDriver(PathFirefox65, Geckdriver024);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		System.setProperty("webdriver.gecko.driver", Geckdriver);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	@Test
	public void PR01() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "Área privada");
	}

	@Test
	public void PR02() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "", "Josefo", "Perez", "77777", "77777");
		driver.findElement(By.id("email")).getAttribute("validationMessage");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "", "Perez", "77777", "77777");
		driver.findElement(By.id("name")).getAttribute("validationMessage");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "", "77777", "77777");
		driver.findElement(By.id("lastName")).getAttribute("validationMessage");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "Perez", "", "77777");
		driver.findElement(By.id("password")).getAttribute("validationMessage");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "Perez", "77777", "");
		driver.findElement(By.id("passwordConfirm")).getAttribute("validationMessage");

	}

	@Test
	public void PR03() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "Perez", "77777", "77577");
		PO_View.checkElement(driver, "text", "Las contraseñas no coinciden");

	}

	@Test
	public void PR04() {
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "Perez", "77777", "77777");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.getP();
		PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary");
		PO_RegisterView.fillForm(driver, "josefo@email.com", "Josefo", "Perez", "77777", "77777");
		PO_View.checkElement(driver, "text", "El email ya está registrado.");
	}

	@Test
	public void PR05() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		PO_View.checkElement(driver, "text", "Área privada");
	}

	@Test
	public void PR06() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		PO_View.checkElement(driver, "text", "Área privada");
	}

	@Test
	public void PR07() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "", "123456");
		driver.findElement(By.id("email")).getAttribute("validationMessage");
		PO_LoginView.fillForm(driver, "gema@email.com", "");
		driver.findElement(By.id("password")).getAttribute("validationMessage");
	}

	@Test
	public void PR08() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "asdf");
		driver.findElement(By.id("alert"));
	}

	@Test
	public void PR09() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "ykygyhfhjfhj@email.com", "123456");
		driver.findElement(By.id("alert"));
	}

	@Test
	public void PR10() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		PO_View.checkElement(driver, "text", "Área privada");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		PO_View.checkElement(driver, "text", "Inicia sesión");
	}

	@Test
	public void PR11() {
		driver.findElements(By.id("logout")).isEmpty();
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		PO_View.checkElement(driver, "text", "Área privada");
		PO_HomeView.clickOption(driver, "logout", "class", "btn btn-primary");
		driver.findElements(By.id("logout")).isEmpty();

	}

	@Test
	public void PR12() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 5);

	}

	@Test
	public void PR13() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 5);
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox']"));
		checks.get(0).click(); // borramos al primer usuario gema@email.com
		driver.findElement(By.id("botonS")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 4);
		assertTrue(userService.getUserByEmail("gema@email.com") == null);
	}

	@Test
	public void PR14() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 5);
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox']"));
		checks.get(checks.size() - 1).click(); // borramos al ultimo usuario pedro@email.com
		driver.findElement(By.id("botonS")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 4);
		assertTrue(userService.getUserByEmail("pedro@email.com") == null);
	}

	@Test
	public void PR15() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "admin@email.com", "admin");
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "users-menu", PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/user/list", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 5);
		List<WebElement> checks = driver.findElements(By.xpath("//input[@type='checkbox']"));
		// Borramos a los 3 primeros usuarios
		checks.get(0).click(); // gema@email.com
		checks.get(1).click(); // cristina@email.com
		checks.get(2).click(); // christian@email.com
		driver.findElement(By.id("botonS")).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='tableUsers']/tbody/tr")).size() == 2);
		assertTrue(userService.getUserByEmail("gema@email.com") == null);
		assertTrue(userService.getUserByEmail("cristina@email.com") == null);
		assertTrue(userService.getUserByEmail("christian@email.com") == null);
	}

	@Test
	public void PR16() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "offersCreating-menu",
				PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/offer/add", "class", "btn btn-primary");
		PO_NewOfferView.fillForm(driver, "Prueba", "prueba nuevo", 50.2);
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 4);
	}

	@Test
	public void PR17() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);

		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", "offersCreating-menu",
				PO_View.getTimeout());
		elementos.get(0).click();
		PO_HomeView.clickOption(driver, "/offer/add", "class", "btn btn-primary");
		PO_NewOfferView.fillForm(driver, "", "prueba nuevo", 50.2);
		driver.findElement(By.id("title")).getAttribute("validationMessage");
		// PO_View.checkElement(driver, "text", "Este campo no puede estar vacío");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);

	}

	@Test
	public void PR18() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);

	}

	@Test
	public void PR19() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);
		driver.findElements(By.linkText("Eliminar")).get(0).click();
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 2);

	}

	@Test
	public void PR20() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 3);
		driver.findElements(By.linkText("Eliminar")).get(driver.findElements(By.linkText("Eliminar")).size() - 1)
				.click();
		assertTrue(driver.findElements(By.xpath("//table[@id='createdOffers']/tbody/tr")).size() == 2);

	}

	@Test
	public void PR21() {
		PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary");
		PO_LoginView.fillForm(driver, "gema@email.com", "123456");
		PO_HomeView.clickOption(driver, "catalogue", "class", "btn btn-primary");
		assertTrue(driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).size() == 12);
		WebElement busqueda = driver.findElement(By.name("searchText"));
		busqueda.click();
		busqueda.clear();
		busqueda.sendKeys("");
		driver.findElement(By.id("botonS")).click();		
		assertTrue(driver.findElements(By.xpath("//table[@id='tableOffers']/tbody/tr")).size() == 12);

	}

}