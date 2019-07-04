package org.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;

@Component
@Data
public class Restaurant {

	@lombok.Setter(onMethod_= {@Autowired})
	private Chef chef;
	
	
	
}
