package org.spring.springboot.controller;

import org.spring.springboot.domain.PersonIndex;
import org.spring.springboot.domain.ResultVO;
import org.spring.springboot.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @RequestMapping(value = "/api/persion", method = RequestMethod.GET)
    public ResultVO person() {
        ResultVO resultVO = new ResultVO();
        Iterable<PersonIndex> personIndices = personService.persionSearch1();

        resultVO.setData(personIndices);
        return resultVO;
    }

    @RequestMapping(value = "/api/persion2", method = RequestMethod.GET)
    public ResultVO person2() {
        PersonIndex personIndex = personService.persionSearch2();
        ResultVO resultVO = new ResultVO();
        resultVO.setData(personIndex);
        return  resultVO;
    }


}
