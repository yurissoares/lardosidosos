package com.ufrb.lardosidosos.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.ufrb.lardosidosos.dto.MoradorDTO;
import com.ufrb.lardosidosos.entity.Morador;
import com.ufrb.lardosidosos.repository.IMoradorRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class MoradorServiceUnitTest {

    @Mock
    private IMoradorRepository moradorRepository;

    @InjectMocks
    private MoradorService moradorService;

    private static Morador morador1;
    private static Morador morador2;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

    @BeforeAll
    public static void init() {

        morador1 = new Morador();
        morador1.setId(1L);
        morador1.setNome("Yuri Santos Soares");
        morador1.setDataEntrada(LocalDate.parse("06/03/2021", formatter));
        morador1.setDataNascimento(LocalDate.parse("12/01/1993", formatter));
        morador1.setNmRg("1389139190");

        morador2 = new Morador();
        morador2.setId(2L);
        morador2.setNome("Georgea Cornelli");
        morador2.setDataEntrada(LocalDate.parse("06/03/2021", formatter));
        morador2.setDataNascimento(LocalDate.parse("12/12/2000", formatter));
        morador2.setNmRg("1111111111");
    }

    @Test
    public void testListarSucesso() {
        List<Morador> listMorador = new ArrayList<>();
        listMorador.add(morador1);
        listMorador.add(morador2);

        Mockito.when(this.moradorRepository.findAll()).thenReturn(listMorador);

        List<MoradorDTO> listMoradorDto = this.moradorService.listar();

        assertNotNull(listMoradorDto);
        assertEquals("Yuri Santos Soares", listMoradorDto.get(0).getNome());
        assertEquals("1389139190", listMoradorDto.get(0).getNmRg());
        assertEquals(2, listMoradorDto.size());

        Mockito.verify(this.moradorRepository, Mockito.times(1)).findAll();

    }

    @Test
    public void testBuscarPorIdSucesso() {
        Mockito.when(this.moradorRepository.findById(2L)).thenReturn(Optional.of(morador2));
        MoradorDTO moradorDto = this.moradorService.buscar(2L);

        assertNotNull(moradorDto);
        assertEquals("Georgea Cornelli", moradorDto.getNome());
        assertEquals("1111111111", moradorDto.getNmRg());

        Mockito.verify(this.moradorRepository, Mockito.times(1)).findById(2L);

    }

    @Test
    public void testBuscarPorNomeSucesso() {
        List<Morador> listMorador = new ArrayList<>();
        listMorador.add(morador1);

        Mockito.when(this.moradorRepository.findByNomeContainingOrderByNomeAsc("Yuri Santos Soares"))
                .thenReturn(listMorador);

        List<MoradorDTO> listMoradorDto = this.moradorService.buscarPorNome("Yuri Santos Soares");

        assertNotNull(listMoradorDto);
        assertEquals("Yuri Santos Soares", listMoradorDto.get(0).getNome());
        assertEquals("1389139190", listMoradorDto.get(0).getNmRg());

        Mockito.verify(this.moradorRepository, Mockito.times(1))
                .findByNomeContainingOrderByNomeAsc("Yuri Santos Soares");
    }

    @Test
    public void testCadastrarSucesso() {
        MoradorDTO moradorDto = new MoradorDTO();
        moradorDto.setNome("Yuri Santos Soares");
        moradorDto.setDataEntrada(LocalDate.parse("06/03/2021", formatter));
        moradorDto.setDataNascimento(LocalDate.parse("12/01/1993", formatter));
        moradorDto.setNmRg("1389139190");

        morador1.setId(null);

        Mockito.when(this.moradorRepository.save(morador1)).thenReturn(morador1);

        Boolean sucesso = this.moradorService.cadastrar(moradorDto);

        assertTrue(sucesso);

        Mockito.verify(this.moradorRepository, Mockito.times(1)).save(morador1);

        morador1.setId(1L);
    }

    @Test
    public void testEditarSucesso() {
        MoradorDTO moradorDto = new MoradorDTO();
        morador1.setId(1L);
        moradorDto.setNome("Yuri Santos Soares");
        moradorDto.setDataEntrada(LocalDate.parse("06/03/2021", formatter));
        moradorDto.setDataNascimento(LocalDate.parse("12/01/1993", formatter));
        moradorDto.setNmRg("1389139190");

        Mockito.when(this.moradorRepository.findById(1L)).thenReturn(Optional.of(morador1));
        Mockito.lenient().when(this.moradorRepository.save(morador1)).thenReturn(morador1);

        Boolean sucesso = this.moradorService.editar(1L, moradorDto);
        assertTrue(sucesso);

        Mockito.verify(this.moradorRepository, Mockito.times(1)).save(morador1);
        Mockito.verify(this.moradorRepository, Mockito.times(1)).findById(1L);

    }

    @Test
    public void testExcluirSucesso() {
        Mockito.when(this.moradorRepository.findById(2L)).thenReturn(Optional.of(morador2));

        Boolean sucesso = this.moradorService.excluir(2L);

        assertTrue(sucesso);

        Mockito.verify(this.moradorRepository, Mockito.times(0)).save(morador1);
        Mockito.verify(this.moradorRepository, Mockito.times(1)).findById(2L);
        Mockito.verify(this.moradorRepository, Mockito.times(1)).deleteById(2L);

    }




}



























