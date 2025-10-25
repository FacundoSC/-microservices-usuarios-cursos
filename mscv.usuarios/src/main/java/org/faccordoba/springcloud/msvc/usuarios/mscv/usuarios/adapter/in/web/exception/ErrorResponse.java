package org.faccordoba.springcloud.msvc.usuarios.mscv.usuarios.adapter.in.web.exception;

import java.time.LocalDateTime;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}