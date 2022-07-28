package com.debpredator.tiendamusicalweb.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

//utileria para fechas del proyecto 
public class DateUtils {

	//convierte fecha en formato string 
	public static String convertDateString(String formato,Date fecha) {
		//formato dd/MM/yyyy hh:mm:ss
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formato);
		
		String fechaConvert = simpleDateFormat.format(fecha);
		
		return fechaConvert;
	}
}
