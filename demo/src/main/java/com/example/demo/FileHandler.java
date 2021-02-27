package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class FileHandler {
    public Path filePath = Paths.get("file.txt");
    public String stringFilePath = filePath.toString();
    public List<String> sourceText = Arrays.asList(
            "REST (от англ. Representational State Transfer — «передача состояния представления»)",
            "архитектурный стиль взаимодействия компонентов распределённого приложения в сети. REST представляет собой согласованный набор",
            "ограничений, учитываемых при проектировании распределённой гипермедиа-системы. В определённых случаях (интернет-магазины,",
            "поисковые системы, прочие системы, основанные на данных) это приводит к повышению производительности и упрощению архитектуры.",
            "В широком смысле[уточнить] компоненты в REST взаимодействуют наподобие взаимодействия клиентов и серверов во Всемирной паутине.",
            "REST является альтернативой RPC[1]. В сети Интернет вызов удалённой процедуры может представлять собой обычный HTTP-запрос (обычно GET или POST;",
            "такой запрос называют «REST-запрос»), а необходимые данные передаются в качестве параметров запроса[2][3]. Для веб-служб, построенных с учётом REST",
            "(то есть не нарушающих накладываемых им ограничений), применяют термин «RESTful». В отличие от веб-сервисов (веб-служб) на основе SOAP, не существует",
            "«официального» стандарта для RESTful веб-API. Дело в том, что REST является архитектурным стилем, в то время как SOAP является протоколом.",
            "Несмотря на то, что REST не является стандартом сам по себе, большинство RESTful-реализаций используют такие стандарты, как HTTP, URL, JSON и XML",
            "(но XML зачастую не принято использовать).");
    private WordSearchRepository wordSearchRepository;

    @Autowired
    public void setWordSearchRepository(WordSearchRepository wordSearchRepository) {
        this.wordSearchRepository = wordSearchRepository;
    }

    @GetMapping("/history")
    public Flux<WordSearch> list() {
        return wordSearchRepository.findAll();
    }

    public Mono<WordSearch> save(@RequestBody WordSearch wordSearch) {
        return wordSearchRepository
                .save(wordSearch);
    }

    public void CreateFile() {
        try {
            Files.write(filePath, sourceText, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/")
    public Flux<String> TextSearch(@RequestParam(value = "word") String word) throws IOException {

        WordSearch wordSearch = new WordSearch(word, stringFilePath);
        save(wordSearch);

        Stream<String> stream = Files.lines(filePath);

        Flux<String> result;

        if (word != null) {
            List<String> search = stream
                    .filter(line -> line.contains(word))
                    .map(line -> line + "\n")
                    .collect(Collectors.toList());
            result = Flux.fromIterable(search);
        } else
            result = Flux.fromStream(stream);

        return result;
    }
}