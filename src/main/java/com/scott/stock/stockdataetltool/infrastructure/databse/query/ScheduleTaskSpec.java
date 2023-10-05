//package com.scott.stock.stockdataetltool.infrastructure.databse.query;
//
//import com.scott.stock.stockdataetltool.model.ScheduleTask;
//import com.scott.stock.stockdataetltool.model.ScheduleTask.TaskStatus;
//import jakarta.persistence.criteria.Predicate;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import org.springframework.data.jpa.domain.Specification;
//
//public final class ScheduleTaskSpec {
//
//    private List<Specification> specs = new ArrayList<>();
//
//    protected ScheduleTaskSpec() {
//    }
//
//    public static ScheduleTaskSpec where() {
//        return new ScheduleTaskSpec();
//    }
//
//    public ScheduleTaskSpec datetimeBetween(Date start, Date end) {
//        Specification<ScheduleTask> spec = (root, query, criteriaBuilder)
//            -> criteriaBuilder.between(root.get("createDatetime"), start, end);
//        specs.add(spec);
//        return this;
//    }
//
//    public ScheduleTaskSpec nameEqual(String name) {
//        Specification<ScheduleTask> spec = (root, query, criteriaBuilder)
//            -> criteriaBuilder.equal(root.get("name"), name);
//        specs.add(spec);
//        return this;
//    }
//
//    public ScheduleTaskSpec statusIn(List<TaskStatus> statuses) {
//        Specification<ScheduleTask> spec = (root, query, criteriaBuilder) -> root.get("status")
//            .in(statuses);
//        specs.add(spec);
//        return this;
//    }
//
//    public Specification<ScheduleTask> build() {
//        Specification<ScheduleTask> spec = (root, query, criteriaBuilder) -> {
//            Predicate[] predicates = specs.stream()
//                .map(s -> s.toPredicate(root, query, criteriaBuilder))
//                .toArray(Predicate[]::new);
//            return criteriaBuilder.and(predicates);
//        };
//        return spec;
//    }
//
//}
