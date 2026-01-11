package io.student.rococo.controller;

import io.student.rococo.model.PaintingJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/painting")
public class PaintingController {

    @GetMapping
    public Page<PaintingJson> getAllPaintings(
            @AuthenticationPrincipal Jwt principal,//ожидаем что приходит сюда наш токен
            @PageableDefault Pageable pageable) {
        return new PageImpl<>(
                List.of(new PaintingJson(
                        null,  // id
                        null,  // title
                        null,  // description
                        null,  // content
                        null,  // artist
                        null   // museum
                )),
                pageable,
                0L  // total
        );
    }
}