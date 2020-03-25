package char13_2_2;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class AuditLogRecord {
    @Id
    @GeneratedValue
    protected Long id;

    @NotNull
    protected String message;

    @NotNull
    protected Long entityId;

    @NotNull
    protected Class entityClass;

    @NotNull
    protected Long userId;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdOn = new Date();

    public AuditLogRecord(@NotNull String message, @NotNull Long entityId, @NotNull Long userId) {
        this.message = message;
        this.entityId = entityId;
        this.userId = userId;
    }
}
