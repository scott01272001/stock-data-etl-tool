package com.scott.stock.stockdataetltool.domain.collectdata.entity;

import com.scott.stock.stockdataetltool.domain.VersionEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "crawler_token")
public class CrawlerToken extends VersionEntity {

    @Id
    @Column
    @SequenceGenerator(name = "token_id_seq", sequenceName = "token_id_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_id_seq")
    private long id;

    @Column(name = "token_account", nullable = false, updatable = false)
    private String tokenAccount;

    @Column(nullable = false, unique = true)
    private String content;

}
