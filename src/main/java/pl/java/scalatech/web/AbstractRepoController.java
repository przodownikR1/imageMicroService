    package pl.java.scalatech.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRepoController<T> {

    private final @NonNull JpaRepository<T, Long> repo;

    public AbstractRepoController(JpaRepository<T, Long> repository) {
        this.repo = repository;
    }

    protected abstract String getView();

    protected abstract String getEditView();

    protected abstract T createEmpty();

    protected abstract String getRedirect();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<T> getAll() {
        List<T> result =  repo.findAll();
        if(result == null || result.isEmpty()) { //TODO some problem with empty collection !!!!
            result  =null;
        }

        return result;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String init(Model model) {
        model.addAttribute(createEmpty());
        return getEditView();
    }

    @RequestMapping(value = "/{id}/test", method = RequestMethod.GET)
    @ResponseBody
    T getProductByIdTest(@PathVariable("id") T t) {
        return t;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    String getObjectId(@PathVariable("id") Long id, Model model) {
        if (id == null) {
            model.addAttribute(createEmpty());
        } else {
            model.addAttribute(repo.findOne(id));
        } 
        return getEditView();
    }

    @RequestMapping(value = { "", "/{id}" }, method = RequestMethod.POST)
    public String create(@Valid T t, BindingResult result, Errors errors) {
        log.info("+++  {} save :  {}", t.getClass().getSimpleName(), t);
        if (result.hasErrors()) {
            log.info("+++  object:{} error  {}", t.getClass().getSimpleName(), result);
            return getEditView();
        }
        repo.save(t);
        return getRedirect();
    }

    @SneakyThrows
    @RequestMapping(value = "/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST })
    public String delete(@PathVariable("id") Long id) {
        T t = repo.findOne(id);
        repo.delete(t);
        return getRedirect();
    }
}
