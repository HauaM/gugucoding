package org.zerock.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/*SampleDTO 객체를 리스트로 파라미터에 입력 받을 경우*/
//http://localhost:8080/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb&list%5B3%5D.name=cccc
@Data
public class SampleDTOList {
	private List<SampleDTO> list;
	
	public SampleDTOList() {
		list = new ArrayList<>();
	}
}
