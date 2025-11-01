package org.faccordoba.springcloud.msvc.cursos.msvc.cursos.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}
