package com.blacknebula.scrumpoker.rest;

import com.blacknebula.scrumpoker.dto.SessionCreationDto;
import com.blacknebula.scrumpoker.security.SecurityContext;
import com.blacknebula.scrumpoker.dto.SessionDto;
import com.blacknebula.scrumpoker.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SessionRestController {

    @Autowired
    private SessionService sessionService;

    /**
     * @return SessionDto
     * @should return 200 status
     * @should return valid error status if an exception has been thrown
     */
    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SessionDto> getSession() {
        return new ResponseEntity<>(sessionService.getSession(), HttpStatus.OK);
    }

    /**
     * @param sessionCreationDto Session that will be created
     * @return SessionDto
     * @should return 200 status
     * @should return valid error status if an exception has been thrown
     */
    @RequestMapping(value = "/sessions", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SessionCreationDto> createSession(@RequestBody SessionCreationDto sessionCreationDto, HttpServletResponse httpServletResponse) {
        return new ResponseEntity<>(sessionService.createSession(sessionCreationDto,
                (token) -> httpServletResponse.addHeader(SecurityContext.Headers.JWT_TOKEN, token)), HttpStatus.OK);
    }
}