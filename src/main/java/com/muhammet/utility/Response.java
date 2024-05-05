package com.muhammet.utility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Response<T> {
    /**
     * 200 -> Başarılı
     * 400 -> Başarısız - Kullanıcı kaynaklı
     * 500 -> Başarısız - Program kaynaklı
     */
    int statusCode;
    /**
     * Başarılı ise olumu mesajlar
     * Başarısız ise nedeni açıklamalı olarak belirtilen mesajlar
     */
    String message;
    /**
     * Eğer başarılı bir şekilde sonlanmış ise kullanıcıya iletilecek bilgi datası.
     */
    T data;
}
