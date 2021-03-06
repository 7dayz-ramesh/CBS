/****** Object:  Table [cbs].[contractor_employee]    Script Date: 22-04-2020 16:05:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor_employee](
	[contractor_employee_id] [bigint] IDENTITY(5000,1) NOT NULL,
	[employee_name] [varchar](255) NOT NULL,
	[contractor_id] [bigint] NOT NULL,
	[contact_details] [varchar](255) NULL,
	[changed_date] [datetime] NOT NULL,
	[changed_by] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[known_as] [varchar](255) NULL,
	[address_line1] [varchar](255) NULL,
	[address_line2] [varchar](255) NULL,
	[address_line3] [varchar](255) NULL,
	[postal_district] [varchar](255) NULL,
	[postal_code] [varchar](255) NULL,
	[country] [varchar](255) NULL,
	[phone] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[representative_undertaking_signed] [varchar](50) NULL,
 CONSTRAINT [PK_CONTRACTOR_EMPLOYEE] PRIMARY KEY CLUSTERED 
(
	[contractor_employee_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[booking_revision]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[booking_revision](
	[booking_revision_id] [bigint] IDENTITY(2000,1) NOT NULL,
	[booking_id] [bigint] NOT NULL,
	[revision_number] [bigint] NOT NULL,
	[contracted_from_date] [datetime] NULL,
	[contracted_to_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
	[currency_id] [bigint] NULL,
	[job_number] [varchar](255) NULL,
	[changed_date] [datetime] NULL,
	[rate] [decimal](18, 0) NULL,
	[approval_status_id] [bigint] NULL,
	[agreement_document_id] [varchar](1000) NULL,
	[agreement_id] [varchar](1000) NULL,
	[contractor_signed_date] [datetime] NULL,
	[inside_ir35] [varchar](50) NULL,
	[contractor_id] [bigint] NULL,
	[contractor_total_available_days] [bigint] NULL,
	[contractor_total_working_days] [bigint] NULL,
	[contract_amount_beforetax] [decimal](18, 0) NULL,
	[contract_amount_aftertax] [decimal](18, 0) NULL,
	[employer_tax_percent] [decimal](18, 0) NULL,
	[job_dept_name] [varchar](50) NULL,
	[appprover_comments] [varchar](255) NULL,
	[supplier_type_id] [bigint] NULL,
	[commissioning_office] [bigint] NULL,
	[contract_work_location] [bigint] NULL,
	[reason_for_recruiting] [bigint] NULL,
	[contract_employee_id] [bigint] NULL,
	[team_id] [bigint] NULL,
	[job_name] [varchar](255) NULL,
	[role_id] [bigint] NULL,
	[comm_off_region] [bigint] NULL,
	[contractor_work_region] [bigint] NULL,
	[completed_agreement_pdf] [varchar](500) NULL,
 CONSTRAINT [PK_BOOKING_REVISION] PRIMARY KEY CLUSTERED 
(
	[booking_revision_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[role_dm]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[role_dm](
	[role_id] [bigint] IDENTITY(3000,1) NOT NULL,
	[role_name] [varchar](255) NULL,
	[role_description] [varchar](255) NULL,
	[changed_date] [datetime] NOT NULL,
	[changed_by] [varchar](255) NOT NULL,
	[discipline_id] [bigint] NOT NULL,
	[inside_ir35] [varchar](50) NULL,
	[status] [char](1) NULL,
 CONSTRAINT [PK_ROLE_DM] PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[approval_status_dm]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[approval_status_dm](
	[approval_status_id] [bigint] IDENTITY(1000,1) NOT NULL,
	[approval_description] [varchar](255) NULL,
	[approval_name] [varchar](255) NULL,
	[changed_by] [varchar](255) NULL,
	[changed_date] [datetime] NULL,
 CONSTRAINT [PK_APPROVAL_STATUS_DM] PRIMARY KEY CLUSTERED 
(
	[approval_status_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[contractor_employee_default_rate]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor_employee_default_rate](
	[rate_id] [bigint] IDENTITY(6000,1) NOT NULL,
	[contractor_employee_id] [bigint] NOT NULL,
	[rate] [decimal](18, 0) NOT NULL,
	[currency_id] [bigint] NULL,
	[date_from] [datetime] NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_CONTRACTOR_EMPLOYEE_DEFAULT_RATE] PRIMARY KEY CLUSTERED 
(
	[rate_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[contractor_employee_role]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor_employee_role](
	[contractor_employee_role_id] [bigint] IDENTITY(2000,1) NOT NULL,
	[role_id] [bigint] NOT NULL,
	[contractor_employee_id] [bigint] NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[date_from] [date] NULL,
 CONSTRAINT [PK_CONTRACTOR_EMPLOYEE_ROLE] PRIMARY KEY CLUSTERED 
(
	[contractor_employee_role_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[contractor]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor](
	[contractor_id] [bigint] IDENTITY(6000,1) NOT NULL,
	[contractor_name] [varchar](255) NOT NULL,
	[company_type] [varchar](255) NOT NULL,
	[contact_details] [varchar](255) NULL,
	[changed_date] [datetime] NOT NULL,
	[changed_by] [varchar](255) NULL,
	[status] [varchar](255) NULL,
	[maconomy_vendor_number] [varchar](50) NULL,
	[address_line1] [varchar](255) NULL,
	[address_line2] [varchar](255) NULL,
	[address_line3] [varchar](255) NULL,
	[postal_district] [varchar](255) NULL,
	[postal_code] [varchar](255) NULL,
	[country] [varchar](255) NULL,
	[attention] [varchar](255) NULL,
	[email] [varchar](255) NULL,
	[on_preferred_supplier_list] [varchar](50) NULL,
 CONSTRAINT [PK_CONTRACTOR] PRIMARY KEY CLUSTERED 
(
	[contractor_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [cbs].[contractor_employee_search]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE VIEW [cbs].[contractor_employee_search] AS   
SELECT ROW_NUMBER() OVER(ORDER BY (SELECT 1)) row_no, ce.contractor_employee_id, ce.employee_name, cedr.rate, rd.role_id, rd.role_name, c.contractor_id, c.contractor_name, no_of_bookings
FROM cbs.contractor_employee ce LEFT OUTER JOIN cbs.contractor AS c ON ce.contractor_id = c.contractor_id 
LEFT OUTER JOIN cbs.contractor_employee_default_rate AS cedr ON ce.contractor_employee_id = cedr.contractor_employee_id
LEFT OUTER JOIN cbs.contractor_employee_role AS cer ON ce.contractor_employee_id = cer.contractor_employee_id
LEFT OUTER JOIN cbs.role_dm AS rd ON cer.role_id = rd.role_id
LEFT OUTER JOIN (SELECT contract_employee_id, COUNT(approval_status_id) AS no_of_bookings 
			FROM cbs.booking_revision WHERE approval_status_id = (SELECT approval_status_id from cbs.approval_status_dm WHERE approval_name = 'Completed') 
			GROUP BY contract_employee_id) as br ON br.contract_employee_id = ce.contractor_employee_id
GO
/****** Object:  Table [cbs].[active_company_teams]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[active_company_teams](
	[id] [bigint] NOT NULL,
	[company_number] [bigint] NOT NULL,
	[team_id] [bigint] NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_active_company_teams] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[approver]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[approver](
	[approver_id] [bigint] IDENTITY(1000,1) NOT NULL,
	[team_id] [bigint] NULL,
	[employee_id] [bigint] NULL,
	[approver_order] [bigint] NOT NULL,
	[changed_date] [datetime] NOT NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_APPROVER] PRIMARY KEY CLUSTERED 
(
	[approver_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[approver_override_jobs]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[approver_override_jobs](
	[override_id] [bigint] NOT NULL,
	[job_number] [varchar](255) NOT NULL,
	[employee_id] [bigint] NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_approver_override_jobs] PRIMARY KEY CLUSTERED 
(
	[override_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[booking]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[booking](
	[booking_id] [bigint] IDENTITY(1000,1) NOT NULL,
	[booking_description] [varchar](255) NULL,
	[team_id] [bigint] NULL,
	[status_id] [bigint] NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_BOOKING] PRIMARY KEY CLUSTERED 
(
	[booking_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[booking_work_tasks]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[booking_work_tasks](
	[booking_work_id] [bigint] IDENTITY(1,1) NOT NULL,
	[booking_revision_id] [bigint] NOT NULL,
	[task_name] [varchar](255) NULL,
	[task_delivery_date] [date] NULL,
	[task_date_rate] [decimal](18, 0) NULL,
	[task_total_days] [bigint] NULL,
	[task_total_amount] [decimal](18, 0) NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_booking_work_tasks] PRIMARY KEY CLUSTERED 
(
	[booking_work_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[company]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[company](
	[company_number] [bigint] IDENTITY(1000,1) NOT NULL,
	[region_id] [bigint] NULL,
	[company_description] [varchar](255) NULL,
	[currency_id] [bigint] NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_company] PRIMARY KEY CLUSTERED 
(
	[company_number] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[config]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[config](
	[config_id] [bigint] IDENTITY(1000,1) NOT NULL,
	[key_name] [varchar](255) NOT NULL,
	[key_value] [varchar](255) NOT NULL,
	[changed_date] [datetime] NULL,
	[key_description] [varchar](255) NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_CONFIG_DM] PRIMARY KEY CLUSTERED 
(
	[config_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[contractor_employee_ratings]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor_employee_ratings](
	[contractor_employee_ratings_id] [bigint] IDENTITY(7000,1) NOT NULL,
	[contractor_employee_id] [bigint] NOT NULL,
	[rating_given] [decimal](18, 0) NOT NULL,
	[rating_given_by] [varchar](255) NULL,
	[rating_given_date] [datetime] NULL,
	[booking_id] [bigint] NOT NULL,
	[description] [varchar](255) NULL,
	[status] [varchar](255) NULL,
 CONSTRAINT [PK_CONTRACTOR_EMPLOYEE_RATINGS] PRIMARY KEY CLUSTERED 
(
	[contractor_employee_ratings_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[contractor_monthly_workdays]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor_monthly_workdays](
	[work_days_id] [bigint] IDENTITY(1,1) NOT NULL,
	[booking_revision_id] [bigint] NOT NULL,
	[month_name] [varchar](255) NOT NULL,
	[month_working_days] [bigint] NOT NULL,
	[changed_by] [varchar](255) NULL,
	[changed_datetime] [datetime] NULL,
 CONSTRAINT [PK_contractor_monthly_workdays] PRIMARY KEY CLUSTERED 
(
	[work_days_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[contractor_work_sites]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[contractor_work_sites](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[booking_revision_id] [bigint] NULL,
	[site_id] [bigint] NULL,
 CONSTRAINT [PK_contractor_work_sites] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[currency_dm]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[currency_dm](
	[currency_id] [bigint] IDENTITY(100,1) NOT NULL,
	[currency_code] [varchar](255) NOT NULL,
	[currency_name] [varchar](255) NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_CURRENCY_DM] PRIMARY KEY CLUSTERED 
(
	[currency_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[discipline]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[discipline](
	[discipline_id] [bigint] IDENTITY(8000,1) NOT NULL,
	[discipline_name] [varchar](255) NOT NULL,
	[discipline_description] [varchar](255) NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_DISCIPLINE] PRIMARY KEY CLUSTERED 
(
	[discipline_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[employee_mapping]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[employee_mapping](
	[employee_id] [bigint] IDENTITY(1000,1) NOT NULL,
	[employee_number_maconomy] [varchar](50) NOT NULL,
	[google_account] [varchar](255) NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_EMPLOYEE_MAPPING] PRIMARY KEY CLUSTERED 
(
	[employee_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[employee_permissions]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[employee_permissions](
	[emp_permission_id] [bigint] NOT NULL,
	[employee_id] [bigint] NOT NULL,
	[company_number] [bigint] NOT NULL,
	[permission_id] [bigint] NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_employee_permissions] PRIMARY KEY CLUSTERED 
(
	[emp_permission_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[office_dm]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[office_dm](
	[office_id] [bigint] IDENTITY(8000,1) NOT NULL,
	[office_name] [varchar](255) NOT NULL,
	[office_description] [varchar](255) NOT NULL,
	[created_date] [datetime] NULL,
	[created_by] [varchar](255) NULL,
	[region_id] [bigint] NULL,
 CONSTRAINT [PK_OFFICE_DM] PRIMARY KEY CLUSTERED 
(
	[office_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[permission]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[permission](
	[permission_id] [bigint] NOT NULL,
	[permission_name] [varchar](255) NOT NULL,
	[permission_description] [varchar](255) NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_permission] PRIMARY KEY CLUSTERED 
(
	[permission_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[reasons_for_recruiting]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[reasons_for_recruiting](
	[reason_id] [bigint] NOT NULL,
	[reason_name] [varchar](255) NOT NULL,
	[reason_description] [varchar](255) NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_reasons_for_recruiting] PRIMARY KEY CLUSTERED 
(
	[reason_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[region]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[region](
	[region_id] [bigint] NOT NULL,
	[region_name] [varchar](255) NULL,
	[region_description] [varchar](255) NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_region] PRIMARY KEY CLUSTERED 
(
	[region_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[role_default_rate]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[role_default_rate](
	[default_rate_id] [bigint] IDENTITY(4000,1) NOT NULL,
	[role_id] [bigint] NOT NULL,
	[rate] [decimal](18, 0) NOT NULL,
	[currency_id] [bigint] NULL,
	[date_from] [datetime] NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_ROLE_DEFAULT_RATE] PRIMARY KEY CLUSTERED 
(
	[default_rate_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[site_options]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[site_options](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[site_name] [varchar](255) NULL,
	[changed_by] [varchar](255) NULL,
	[changed_datetime] [datetime] NULL,
 CONSTRAINT [PK_site_options] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[supplier_type_dm]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[supplier_type_dm](
	[id] [bigint] NOT NULL,
	[name] [varchar](255) NOT NULL,
	[description] [nchar](10) NULL,
 CONSTRAINT [PK_supplier_type_dm] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[tax_rate_dm]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[tax_rate_dm](
	[id] [bigint] IDENTITY(1,1) NOT NULL,
	[rate_percent] [decimal](18, 0) NULL,
	[tax_description] [varchar](255) NULL,
 CONSTRAINT [PK_tax_rate_dm] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [cbs].[team]    Script Date: 22-04-2020 16:05:50 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [cbs].[team](
	[team_id] [bigint] IDENTITY(1000,1) NOT NULL,
	[team_name] [varchar](255) NOT NULL,
	[changed_date] [datetime] NULL,
	[changed_by] [varchar](255) NULL,
 CONSTRAINT [PK_TEAM] PRIMARY KEY CLUSTERED 
(
	[team_id] ASC
)WITH (STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [cbs].[active_company_teams] ADD  CONSTRAINT [DF_active_company_teams_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[approval_status_dm] ADD  CONSTRAINT [DF_approval_status_dm_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[approver] ADD  CONSTRAINT [DF_approver_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[approver_override_jobs] ADD  CONSTRAINT [DF_approver_override_jobs_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[booking] ADD  CONSTRAINT [DF_booking_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[booking_work_tasks] ADD  CONSTRAINT [DF_booking_work_tasks_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[company] ADD  CONSTRAINT [DF_company_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[contractor] ADD  CONSTRAINT [DF_contractor_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[contractor_employee] ADD  CONSTRAINT [DF_contractor_employee_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[contractor_employee_ratings] ADD  CONSTRAINT [DF_contractor_employee_ratings_rating_given_date]  DEFAULT (getdate()) FOR [rating_given_date]
GO
ALTER TABLE [cbs].[contractor_employee_role] ADD  CONSTRAINT [DF_contractor_employee_role_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[contractor_monthly_workdays] ADD  CONSTRAINT [DF_contractor_monthly_workdays_changed_datetime]  DEFAULT (getdate()) FOR [changed_datetime]
GO
ALTER TABLE [cbs].[discipline] ADD  CONSTRAINT [DF_discipline_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[employee_mapping] ADD  CONSTRAINT [DF_employee_mapping_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[employee_permissions] ADD  CONSTRAINT [DF_employee_permissions_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[office_dm] ADD  CONSTRAINT [DF_office_dm_created_date]  DEFAULT (getdate()) FOR [created_date]
GO
ALTER TABLE [cbs].[permission] ADD  CONSTRAINT [DF_permission_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[reasons_for_recruiting] ADD  CONSTRAINT [DF_reasons_for_recruiting_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[region] ADD  CONSTRAINT [DF_region_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[role_default_rate] ADD  CONSTRAINT [DF_role_default_rate_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[role_dm] ADD  CONSTRAINT [DF_role_dm_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[site_options] ADD  CONSTRAINT [DF_site_options_changed_datetime]  DEFAULT (getdate()) FOR [changed_datetime]
GO
ALTER TABLE [cbs].[team] ADD  CONSTRAINT [DF_team_changed_date]  DEFAULT (getdate()) FOR [changed_date]
GO
ALTER TABLE [cbs].[active_company_teams]  WITH CHECK ADD  CONSTRAINT [FK_active_company_teams_company] FOREIGN KEY([company_number])
REFERENCES [cbs].[company] ([company_number])
GO
ALTER TABLE [cbs].[active_company_teams] CHECK CONSTRAINT [FK_active_company_teams_company]
GO
ALTER TABLE [cbs].[active_company_teams]  WITH CHECK ADD  CONSTRAINT [FK_active_company_teams_team] FOREIGN KEY([team_id])
REFERENCES [cbs].[team] ([team_id])
GO
ALTER TABLE [cbs].[active_company_teams] CHECK CONSTRAINT [FK_active_company_teams_team]
GO
ALTER TABLE [cbs].[approver]  WITH CHECK ADD  CONSTRAINT [FK_approver_employee_mapping] FOREIGN KEY([employee_id])
REFERENCES [cbs].[employee_mapping] ([employee_id])
GO
ALTER TABLE [cbs].[approver] CHECK CONSTRAINT [FK_approver_employee_mapping]
GO
ALTER TABLE [cbs].[approver]  WITH CHECK ADD  CONSTRAINT [FK_approver_team] FOREIGN KEY([team_id])
REFERENCES [cbs].[team] ([team_id])
GO
ALTER TABLE [cbs].[approver] CHECK CONSTRAINT [FK_approver_team]
GO
ALTER TABLE [cbs].[approver_override_jobs]  WITH CHECK ADD  CONSTRAINT [FK_approver_override_jobs_employee_mapping] FOREIGN KEY([employee_id])
REFERENCES [cbs].[employee_mapping] ([employee_id])
GO
ALTER TABLE [cbs].[approver_override_jobs] CHECK CONSTRAINT [FK_approver_override_jobs_employee_mapping]
GO
ALTER TABLE [cbs].[booking]  WITH CHECK ADD  CONSTRAINT [FK_booking_approval_status_dm] FOREIGN KEY([status_id])
REFERENCES [cbs].[approval_status_dm] ([approval_status_id])
GO
ALTER TABLE [cbs].[booking] CHECK CONSTRAINT [FK_booking_approval_status_dm]
GO
ALTER TABLE [cbs].[booking]  WITH CHECK ADD  CONSTRAINT [FK_booking_team] FOREIGN KEY([team_id])
REFERENCES [cbs].[team] ([team_id])
GO
ALTER TABLE [cbs].[booking] CHECK CONSTRAINT [FK_booking_team]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_approval_status_dm] FOREIGN KEY([approval_status_id])
REFERENCES [cbs].[approval_status_dm] ([approval_status_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_approval_status_dm]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_contractor] FOREIGN KEY([contractor_id])
REFERENCES [cbs].[contractor] ([contractor_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_contractor]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_contractor_employee] FOREIGN KEY([contract_employee_id])
REFERENCES [cbs].[contractor_employee] ([contractor_employee_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_contractor_employee]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_currency_dm] FOREIGN KEY([currency_id])
REFERENCES [cbs].[currency_dm] ([currency_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_currency_dm]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_office_dm] FOREIGN KEY([commissioning_office])
REFERENCES [cbs].[office_dm] ([office_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_office_dm]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_office_dm1] FOREIGN KEY([contract_work_location])
REFERENCES [cbs].[office_dm] ([office_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_office_dm1]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_reasons_for_recruiting] FOREIGN KEY([reason_for_recruiting])
REFERENCES [cbs].[reasons_for_recruiting] ([reason_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_reasons_for_recruiting]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_region] FOREIGN KEY([comm_off_region])
REFERENCES [cbs].[region] ([region_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_region]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_region1] FOREIGN KEY([contractor_work_region])
REFERENCES [cbs].[region] ([region_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_region1]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_role_dm] FOREIGN KEY([role_id])
REFERENCES [cbs].[role_dm] ([role_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_role_dm]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_supplier_type_dm] FOREIGN KEY([supplier_type_id])
REFERENCES [cbs].[supplier_type_dm] ([id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_supplier_type_dm]
GO
ALTER TABLE [cbs].[booking_revision]  WITH CHECK ADD  CONSTRAINT [FK_booking_revision_team] FOREIGN KEY([team_id])
REFERENCES [cbs].[team] ([team_id])
GO
ALTER TABLE [cbs].[booking_revision] CHECK CONSTRAINT [FK_booking_revision_team]
GO
ALTER TABLE [cbs].[booking_work_tasks]  WITH CHECK ADD  CONSTRAINT [FK_booking_work_tasks_booking_revision] FOREIGN KEY([booking_revision_id])
REFERENCES [cbs].[booking_revision] ([booking_revision_id])
GO
ALTER TABLE [cbs].[booking_work_tasks] CHECK CONSTRAINT [FK_booking_work_tasks_booking_revision]
GO
ALTER TABLE [cbs].[company]  WITH CHECK ADD  CONSTRAINT [FK_company_currency_dm] FOREIGN KEY([currency_id])
REFERENCES [cbs].[currency_dm] ([currency_id])
GO
ALTER TABLE [cbs].[company] CHECK CONSTRAINT [FK_company_currency_dm]
GO
ALTER TABLE [cbs].[company]  WITH CHECK ADD  CONSTRAINT [FK_company_region] FOREIGN KEY([region_id])
REFERENCES [cbs].[region] ([region_id])
GO
ALTER TABLE [cbs].[company] CHECK CONSTRAINT [FK_company_region]
GO
ALTER TABLE [cbs].[contractor_employee]  WITH CHECK ADD  CONSTRAINT [FK_contractor_employee_contractor] FOREIGN KEY([contractor_id])
REFERENCES [cbs].[contractor] ([contractor_id])
GO
ALTER TABLE [cbs].[contractor_employee] CHECK CONSTRAINT [FK_contractor_employee_contractor]
GO
ALTER TABLE [cbs].[contractor_employee_default_rate]  WITH CHECK ADD  CONSTRAINT [FK_contractor_employee_default_rate_contractor_employee] FOREIGN KEY([contractor_employee_id])
REFERENCES [cbs].[contractor_employee] ([contractor_employee_id])
GO
ALTER TABLE [cbs].[contractor_employee_default_rate] CHECK CONSTRAINT [FK_contractor_employee_default_rate_contractor_employee]
GO
ALTER TABLE [cbs].[contractor_employee_ratings]  WITH CHECK ADD  CONSTRAINT [FK_contractor_employee_ratings_booking] FOREIGN KEY([booking_id])
REFERENCES [cbs].[booking] ([booking_id])
GO
ALTER TABLE [cbs].[contractor_employee_ratings] CHECK CONSTRAINT [FK_contractor_employee_ratings_booking]
GO
ALTER TABLE [cbs].[contractor_employee_ratings]  WITH CHECK ADD  CONSTRAINT [FK_contractor_employee_ratings_contractor_employee] FOREIGN KEY([contractor_employee_id])
REFERENCES [cbs].[contractor_employee] ([contractor_employee_id])
GO
ALTER TABLE [cbs].[contractor_employee_ratings] CHECK CONSTRAINT [FK_contractor_employee_ratings_contractor_employee]
GO
ALTER TABLE [cbs].[contractor_employee_role]  WITH CHECK ADD  CONSTRAINT [FK_contractor_employee_role_contractor_employee] FOREIGN KEY([contractor_employee_id])
REFERENCES [cbs].[contractor_employee] ([contractor_employee_id])
GO
ALTER TABLE [cbs].[contractor_employee_role] CHECK CONSTRAINT [FK_contractor_employee_role_contractor_employee]
GO
ALTER TABLE [cbs].[contractor_employee_role]  WITH CHECK ADD  CONSTRAINT [FK_contractor_employee_role_role_dm] FOREIGN KEY([role_id])
REFERENCES [cbs].[role_dm] ([role_id])
GO
ALTER TABLE [cbs].[contractor_employee_role] CHECK CONSTRAINT [FK_contractor_employee_role_role_dm]
GO
ALTER TABLE [cbs].[contractor_monthly_workdays]  WITH CHECK ADD  CONSTRAINT [FK_contractor_monthly_workdays_booking_revision] FOREIGN KEY([booking_revision_id])
REFERENCES [cbs].[booking_revision] ([booking_revision_id])
GO
ALTER TABLE [cbs].[contractor_monthly_workdays] CHECK CONSTRAINT [FK_contractor_monthly_workdays_booking_revision]
GO
ALTER TABLE [cbs].[contractor_work_sites]  WITH CHECK ADD  CONSTRAINT [FK_contractor_work_sites_booking_revision] FOREIGN KEY([booking_revision_id])
REFERENCES [cbs].[booking_revision] ([booking_revision_id])
GO
ALTER TABLE [cbs].[contractor_work_sites] CHECK CONSTRAINT [FK_contractor_work_sites_booking_revision]
GO
ALTER TABLE [cbs].[contractor_work_sites]  WITH CHECK ADD  CONSTRAINT [FK_contractor_work_sites_site_options] FOREIGN KEY([site_id])
REFERENCES [cbs].[site_options] ([id])
GO
ALTER TABLE [cbs].[contractor_work_sites] CHECK CONSTRAINT [FK_contractor_work_sites_site_options]
GO
ALTER TABLE [cbs].[employee_permissions]  WITH CHECK ADD  CONSTRAINT [FK_employee_permissions_company] FOREIGN KEY([company_number])
REFERENCES [cbs].[company] ([company_number])
GO
ALTER TABLE [cbs].[employee_permissions] CHECK CONSTRAINT [FK_employee_permissions_company]
GO
ALTER TABLE [cbs].[employee_permissions]  WITH CHECK ADD  CONSTRAINT [FK_employee_permissions_employee_mapping] FOREIGN KEY([employee_id])
REFERENCES [cbs].[employee_mapping] ([employee_id])
GO
ALTER TABLE [cbs].[employee_permissions] CHECK CONSTRAINT [FK_employee_permissions_employee_mapping]
GO
ALTER TABLE [cbs].[employee_permissions]  WITH CHECK ADD  CONSTRAINT [FK_employee_permissions_permission] FOREIGN KEY([permission_id])
REFERENCES [cbs].[permission] ([permission_id])
GO
ALTER TABLE [cbs].[employee_permissions] CHECK CONSTRAINT [FK_employee_permissions_permission]
GO
ALTER TABLE [cbs].[office_dm]  WITH CHECK ADD  CONSTRAINT [FK_office_dm_region] FOREIGN KEY([region_id])
REFERENCES [cbs].[region] ([region_id])
GO
ALTER TABLE [cbs].[office_dm] CHECK CONSTRAINT [FK_office_dm_region]
GO
ALTER TABLE [cbs].[role_default_rate]  WITH CHECK ADD  CONSTRAINT [FK_role_default_rate_currency_dm] FOREIGN KEY([currency_id])
REFERENCES [cbs].[currency_dm] ([currency_id])
GO
ALTER TABLE [cbs].[role_default_rate] CHECK CONSTRAINT [FK_role_default_rate_currency_dm]
GO
ALTER TABLE [cbs].[role_default_rate]  WITH CHECK ADD  CONSTRAINT [FK_role_default_rate_role_dm] FOREIGN KEY([role_id])
REFERENCES [cbs].[role_dm] ([role_id])
GO
ALTER TABLE [cbs].[role_default_rate] CHECK CONSTRAINT [FK_role_default_rate_role_dm]
GO
ALTER TABLE [cbs].[role_dm]  WITH CHECK ADD  CONSTRAINT [FK_role_dm_discipline] FOREIGN KEY([discipline_id])
REFERENCES [cbs].[discipline] ([discipline_id])
GO
ALTER TABLE [cbs].[role_dm] CHECK CONSTRAINT [FK_role_dm_discipline]
GO
