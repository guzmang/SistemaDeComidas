package com.utndds.condiciones;

import javax.persistence.*;

@DiscriminatorValue("Celiaco")
public class Celiaco extends Condicion {
	
	public String getName() {
		return "Celiaco";

	}
}
