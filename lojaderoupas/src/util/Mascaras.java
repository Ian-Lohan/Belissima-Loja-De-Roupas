package util;

import java.text.DecimalFormat;

public class Mascaras {

	public String converterVirgulaParaPonto (String pString) {
		String retorno = new String();
		int tamanhoString = pString.length();
		for (int i = 0; i < tamanhoString; i++) {
			if (pString.charAt(i) == ',') {
				retorno += '.';
			} else {
				retorno += pString.charAt(i);
			}
		}
		return retorno;
	}
	
	
	public String converterPontoParaVirgula (String pString) {
		String retorno = new String();
		int tamanhoString = pString.length();
		for (int i = 0; i < tamanhoString; i++) {
			if (pString.charAt(i) == '.') {
				retorno += ',';
			} else {
				retorno += pString.charAt(i);
			}
		}
		return retorno;
	}
	
	public float converterVirgulaParaPontoFloat (String pString) {
		String retorno = new String();
		int tamanhoString = pString.length();
		for (int i = 0; i < tamanhoString; i++) {
			if (pString.charAt(i) == '.') {
				retorno += ',';
			} else {
				retorno += pString.charAt(i);
			}
		}
		return Float.parseFloat(retorno);
	}
	
	public String removerPontos (String pString) {
		String retorno = new String();
		int tamanhoString = pString.length();
		for (int i = 0; i < tamanhoString; i++) {
			if (pString.charAt(i) == '.') {
				retorno += "";
			} else {
				retorno += pString.charAt(i);
			}
		}
		return retorno;
	}
	
	public String addPonto (String pString) {
		int pontoConter = 0;
		for (int i = 0; i < pString.length(); i++) {
			if (pString.charAt(i) == '.') {
				pontoConter++;
			}
		}
		if (pontoConter == 0) {
			pString += ".0";
		}
		return pString;
	}
	
	public double truncar3casas (double pValor) {
		return Math.round(pValor * 100)/100d;
	}
	
	public int converterInteiro (int pString) {
		DecimalFormat df = new DecimalFormat(".0");
		pString = Integer.parseInt(df.format(pString));
		return pString;
	}
	
	public double converterArredondar2Casa (double pDouble) {
		DecimalFormat df = new DecimalFormat(".00");
		pDouble = Double.parseDouble(df.format(pDouble));
		return pDouble;
	}
	
	public float converterArredondar2CasaPonto (float pValor) {
		DecimalFormat df = new DecimalFormat(".00");
		return Float.parseFloat(this.converterVirgulaParaPonto(df.format(pValor)));
	}
	
	public double converterArredondar2CasaPontoDouble (double pDouble) {
		DecimalFormat df = new DecimalFormat(".00");
		return Double.parseDouble(this.converterVirgulaParaPonto(df.format(pDouble)));
	}
	
	public String converterArredondar2CasaPontoString (float pValor) {
		DecimalFormat df = new DecimalFormat(".00");
		return this.converterVirgulaParaPonto(df.format(pValor));
	}
	
	public String converterArredondar2CasaPontoDoubleString (double pValor) {
		DecimalFormat df = new DecimalFormat(".00");
		return this.converterVirgulaParaPonto(df.format(pValor));
	}
	
	public float converterArredondar3CasaPonto (float pValor) {
		DecimalFormat df = new DecimalFormat(".000");
		return Float.parseFloat(this.converterVirgulaParaPonto(df.format(pValor)));
	}
}
