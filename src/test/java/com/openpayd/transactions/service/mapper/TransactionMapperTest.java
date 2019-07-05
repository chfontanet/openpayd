package com.openpayd.transactions.service.mapper;

import com.openpayd.transactions.persistence.model.Transaction;
import com.openpayd.transactions.rest.dto.TransactionDto;
import org.junit.Test;

import java.util.List;

import static com.google.common.collect.ImmutableList.of;
import static com.openpayd.transactions.service.ModelHelper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TransactionMapperTest {

    private final TransactionMapper transactionMapper = new TransactionMapper();

    @Test
    public void testToDtoOk() {
        final TransactionDto transactionDto = transactionMapper.toDto(getTransaction());
        assertEquals(getTransactionDto(), transactionDto);
    }

    @Test
    public void testFromDtoOk() {
        final Transaction transaction = transactionMapper.fromDto(getTransactionDto());
        assertEquals(getTransactionTransformed(), transaction);
    }

    @Test
    public void testToDtoListOk() {
        final Long id2 = 2L;
        final Long id3 = 3L;
        final List<TransactionDto> dtoList = transactionMapper.toDtoList(of(getTransaction(), getTransaction(id2), getTransaction(id3)));
        assertEquals(3, dtoList.size());
        assertEquals(ID, dtoList.get(0).getId());
        assertEquals(id2, dtoList.get(1).getId());
        assertEquals(id3, dtoList.get(2).getId());
    }

    @Test
    public void testToDtoListEmptyOk() {
        final List<TransactionDto> transactionDtos = transactionMapper.toDtoList(of());
        assertTrue(transactionDtos.isEmpty());
    }
}