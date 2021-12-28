package kitchenpos.tablegroup.application;

import kitchenpos.common.OrderErrorCode;
import kitchenpos.exception.NotFoundException;
import kitchenpos.tablegroup.domain.TableGroupRepository;
import kitchenpos.tablegroup.domain.TableGroup;
import kitchenpos.tablegroup.domain.TableGroupValidator;
import kitchenpos.tablegroup.dto.TableGroupRequest;
import kitchenpos.tablegroup.dto.TableGroupResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class TableGroupService {

    private final TableGroupRepository tableGroupRepository;
    private final TableGroupValidator tableGroupValidator;

    public TableGroupService(
        final TableGroupRepository orderTableRepository,
        final TableGroupValidator tableGroupValidator
    ) {
        this.tableGroupRepository = orderTableRepository;
        this.tableGroupValidator = tableGroupValidator;
    }

    @Transactional
    public TableGroupResponse create(final TableGroupRequest tableGroup) {
        TableGroup savedTableGroup = tableGroupRepository.save(TableGroup.of());

        savedTableGroup.group(tableGroupValidator, tableGroup.getOrderTableIds());
        tableGroupRepository.save(savedTableGroup);
        return TableGroupResponse.of(savedTableGroup);
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        TableGroup tableGroup = tableGroupRepository.findById(tableGroupId)
            .orElseThrow(
                () -> new NotFoundException(OrderErrorCode.TABLE_GROUP_NOT_FOUND_EXCEPTION));

        tableGroup.ungroup(tableGroupValidator);

        tableGroupRepository.save(tableGroup);
    }
}
